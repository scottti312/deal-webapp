package com.example.DisplayProducts;

import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.CognitoIdentityProviderException;
import software.amazon.awssdk.services.cognitoidentityprovider.model.SignUpRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminInitiateAuthRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AttributeType;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AuthFlowType;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

public class CognitoAuth {
    String clientId = "7cuj1pu58j6n7i1sv7tfhknq8g";
    String secretKey = "1m9k7peq0cdd3t92bpm26skvderdg5ikd546fbvrqda11j0mlls3";
    String userPool = "us-east-1_MZqcCtwmz";

    public static void signUp(CognitoIdentityProviderClient identityProviderClient,
                                  String clientId,
                                  String secretKey,
                                  String userName,
                                  String password,
                                  String email) {

        AttributeType attributeType = AttributeType.builder()
                .name("email")
                .value(email)
                .build();

        List<AttributeType> attrs = new ArrayList<>();
        attrs.add(attributeType);

       try {
           String secretVal = calculateSecretHash(clientId, secretKey, userName);
           SignUpRequest signUpRequest = SignUpRequest.builder()
                   .userAttributes(attrs)
                   .username(userName)
                   .clientId(clientId)
                   .password(password)
                   .secretHash(secretVal)
                   .build();

           identityProviderClient.signUp(signUpRequest);
           System.out.println("User has been signed up");

       } catch(CognitoIdentityProviderException e) {
           System.err.println(e.awsErrorDetails().errorMessage());
           System.exit(1);
       }
    }

    public static String calculateSecretHash(String userPoolClientId, String userPoolClientSecret, String userName) {
        final String HMAC_SHA256_ALGORITHM = "HmacSHA256";

        SecretKeySpec signingKey = new SecretKeySpec(
                userPoolClientSecret.getBytes(StandardCharsets.UTF_8),
                HMAC_SHA256_ALGORITHM);
        try {
            Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
            mac.init(signingKey);
            mac.update(userName.getBytes(StandardCharsets.UTF_8));
            byte[] rawHmac = mac.doFinal(userPoolClientId.getBytes(StandardCharsets.UTF_8));
            return java.util.Base64.getEncoder().encodeToString(rawHmac);
        } catch (Exception e) {
            throw new RuntimeException("Error while calculating ");
        }
    }

    // public static Map<String, String> login(String email, String password) {
    //     Map<String, String> authParams = new LinkedHashMap<String, String>() {{
    //         put("USERNAME", email);
    //         put("PASSWORD", password);
    //     }};

    //     AdminInitiateAuthRequest authRequest = new AdminInitiateAuthRequest()
    //             .withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH)
    //             .withUserPoolId(userPool)
    //             .withClientId(clientId)
    //             .withAuthParameters(authParams);
    //     AdminInitiateAuthResult authResult = client.adminInitiateAuth(authRequest);
    //     AuthenticationResultType resultType = authResult.getAuthenticationResult();
    //      return new LinkedHashMap<String, String>() {{
    //         put("idToken", resultType.getIdToken());
    //         put("accessToken", resultType.getAccessToken());
    //         put("refreshToken", resultType.getRefreshToken());
    //         put("message", "Successfully login");
    //     }};
    // }
}
