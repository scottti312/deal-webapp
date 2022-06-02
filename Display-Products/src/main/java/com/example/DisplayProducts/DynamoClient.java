package com.example.DisplayProducts;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.DeleteItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.ResourceNotFoundException;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamoClient {
    private final DynamoDbClient ddb;

    public DynamoClient() {
        ddb = createDynamoClient();
    }

    private DynamoDbClient createDynamoClient() {
        ProfileCredentialsProvider credentialsProvider = ProfileCredentialsProvider.create();
        Region region = Region.US_WEST_2;
        return DynamoDbClient.builder()
                .credentialsProvider(credentialsProvider)
                .region(region)
                .build();
    }

    public void putItemInTable(String email,
                               String productLinkValue,
                               String productTitleValue,
                               String imageLinkValue,
                               String priceValue,
                               String vendorValue) {
        HashMap<String, AttributeValue> itemValues = new HashMap<>();
        String prehash = productLinkValue + email;
        String generatedString = Integer.toString(prehash.hashCode());
        itemValues.put("entryId", AttributeValue.builder().s(generatedString).build());
        itemValues.put("userId", AttributeValue.builder().s(email).build());
        itemValues.put("productLink", AttributeValue.builder().s(productLinkValue).build());
        itemValues.put("productTitle", AttributeValue.builder().s(productTitleValue).build());
        itemValues.put("imageLink", AttributeValue.builder().s(imageLinkValue).build());
        itemValues.put("price", AttributeValue.builder().s(priceValue).build());
        itemValues.put("vendor", AttributeValue.builder().s(vendorValue).build());
        PutItemRequest request = PutItemRequest.builder()
                .tableName("deal_wishlist")
                .item(itemValues)
                .build();

        try {
            ddb.putItem(request);
            System.out.println("deal-wishlist was successfully updated");

        } catch (ResourceNotFoundException e) {
            System.err.format("Error: The Amazon DynamoDB table \"deal-wishlist\" can't be found.\n");
            System.err.println("Be sure that it exists and that you've typed its name correctly!");
            System.exit(1);
        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public void deleteItem(String keyVal, String email)  {
        HashMap<String,AttributeValue> keyToGet = new HashMap<>();
        keyToGet.put("entryId", AttributeValue.builder()
                .s(keyVal)
                .build());
        keyToGet.put("userId", AttributeValue.builder()
                .s(email)
                .build());
        DeleteItemRequest deleteReq = DeleteItemRequest.builder()
                .tableName("deal_wishlist")
                .key(keyToGet)
                .build();

        try {
            ddb.deleteItem(deleteReq);
        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public List<Map<String, AttributeValue>> scanTable(String tableName, String userId) {
        try  {
            Map<String, AttributeValue> expressionAttributeValues = new HashMap<String, AttributeValue>();
            expressionAttributeValues.put(":userId", AttributeValue.builder().s(userId).build());

            HashMap<String,String> attrNameAlias = new HashMap<String,String>();
            attrNameAlias.put("#a", "userId");


            ScanRequest scanRequest = ScanRequest.builder()
                    .tableName(tableName)
                    .filterExpression("#a = :userId")
                    .expressionAttributeNames(attrNameAlias)
                    .expressionAttributeValues(expressionAttributeValues)
                    .build();

            ScanResponse response = ddb.scan(scanRequest);
            // for (Map<String, AttributeValue> item : response.items()) {
            //     Set<String> keys = item.keySet();
            //     for (String key : keys) {
            //         System.out.println ("The key name is "+key +"\n" );
            //         System.out.println("The value is "+item.get(key).s());
            //     }
            // }
            return response.items();

        } catch (DynamoDbException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}