package com.example.DisplayProducts;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.*;

import software.amazon.awssdk.services.cognitoidentityprovider.model.RevokeTokenRequest;

import java.util.LinkedHashMap;
import java.util.Map;

public class CognitoClient {

    private final AWSCognitoIdentityProvider client ;
    private final String clientId = "clientId";
    private final String userPool = "userPool";
    public static boolean loggedIn = false;
    public static String idToken = "";
    public static String accessToken = "";
    public static String refreshToken = "";
    public CognitoClient() {
        client = createCognitoClient();
    }

    private AWSCognitoIdentityProvider createCognitoClient() {
        AWSCredentials cred = new BasicAWSCredentials("accessKey", "secretKey");
        AWSCredentialsProvider credProvider = new AWSStaticCredentialsProvider(cred);
        return AWSCognitoIdentityProviderClientBuilder.standard()
                .withCredentials(credProvider)
                .withRegion(Regions.US_EAST_1)
                .build();
    }

    public SignUpResult signUp(String name, String email, String password) {
        SignUpRequest request = new SignUpRequest().withClientId(clientId ).withUsername(email).withPassword(password);
        SignUpResult result = client.signUp(request);
        return result;
    }

    public ConfirmSignUpResult confirmSignUp(String email, String confirmationCode) {
        ConfirmSignUpRequest confirmSignUpRequest = new ConfirmSignUpRequest().withClientId(clientId).withUsername(email).withConfirmationCode(confirmationCode);
        return client.confirmSignUp(confirmSignUpRequest);
    }

    public Map<String, String> login(String email, String password) {
        Map<String, String> authParams = new LinkedHashMap<String, String>() {{
            put("USERNAME", email);
            put("PASSWORD", password);
        }};

        AdminInitiateAuthRequest authRequest = new AdminInitiateAuthRequest()
                .withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH)
                .withUserPoolId(userPool)
                .withClientId(clientId)
                .withAuthParameters(authParams);
        AdminInitiateAuthResult authResult = client.adminInitiateAuth(authRequest);
        AuthenticationResultType resultType = authResult.getAuthenticationResult();
        System.out.println(resultType);
        loggedIn = true;
         return new LinkedHashMap<String, String>() {{
            put("idToken", resultType.getIdToken());
            put("accessToken", resultType.getAccessToken());
            put("refreshToken", resultType.getRefreshToken());
            put("message", "Successfully login");
        }};

    }

    public void logout(String token) {
        loggedIn = false;
    }
}