package com.example.DisplayProducts;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.QueryRequest;
import software.amazon.awssdk.services.dynamodb.model.QueryResponse;
import software.amazon.awssdk.services.dynamodb.model.ResourceNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DynamoClient {
    private final DynamoDbClient ddb;
    public DynamoClient() {
        ddb = createDynamoClient();
    }
    // public static void main(String args[]) {
    //     String tableName = "deal-wishlist";
    //     String key = "userid";
    //     String keyVal = "userid01239568123509812350";
    //     String partitionAlias = "#a";
    //     String productLink = "productlink";
    //     String productLinkValue = "asdfasdf";
    //     String productTitle = "iladrhj oiajdsfg";
    //     String price = "5.32";
    //     String vendor = "bestbuy";
    //     String imageLink = "imagelink.com";
    //     System.out.format("Retrieving item \"%s\" from \"%s\"\n",
    //             keyVal, tableName);

    //     ProfileCredentialsProvider credentialsProvider = ProfileCredentialsProvider.create();
    //     Region region = Region.US_WEST_2;
    //     DynamoDbClient ddb = DynamoDbClient.builder()
    //             .credentialsProvider(credentialsProvider)
    //             .region(region)
    //             .build();
    //     System.out.println("Number of results = " + queryTable(ddb, tableName, key, keyVal, partitionAlias));
    //     // putItemInTable(ddb, tableName, key, keyVal, productLink, productLinkValue);
    //     // getDynamoDBItem(ddb, tableName, key, keyVal, productLink, productLinkValue);
    //     ddb.close();
    // }

    private DynamoDbClient createDynamoClient() {
        ProfileCredentialsProvider credentialsProvider = ProfileCredentialsProvider.create();
        Region region = Region.US_WEST_2;
        return DynamoDbClient.builder()
                .credentialsProvider(credentialsProvider)
                .region(region)
                .build();
    }

    public void putItemInTable(String tableName,
                                      String key,
                                      String keyVal,
                                      String productLink,
                                      String productLinkValue,
                                      String productTitle,
                                      String productTitleValue,
                                      String imageLink,
                                      String imageLinkValue,
                                      String price,
                                      String priceValue,
                                      String vendor,
                                      String vendorValue) {
        HashMap<String, AttributeValue> itemValues = new HashMap<>();
        itemValues.put(key, AttributeValue.builder().s(keyVal).build());
        itemValues.put(productLink, AttributeValue.builder().s(productLinkValue).build());
        itemValues.put(productTitle, AttributeValue.builder().s(productTitleValue).build());
        itemValues.put(imageLink, AttributeValue.builder().s(imageLinkValue).build());
        itemValues.put(price, AttributeValue.builder().s(priceValue).build());
        itemValues.put(vendor, AttributeValue.builder().s(vendorValue).build());
        PutItemRequest request = PutItemRequest.builder()
                .tableName(tableName)
                .item(itemValues)
                .build();

        try {
            ddb.putItem(request);
            System.out.println(tableName +" was successfully updated");

        } catch (ResourceNotFoundException e) {
            System.err.format("Error: The Amazon DynamoDB table \"%s\" can't be found.\n", tableName);
            System.err.println("Be sure that it exists and that you've typed its name correctly!");
            System.exit(1);
        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public void getDynamoDBItem(String tableName,
                                String key,
                                String keyVal,
                                String productLink,
                                String productLinkValue,
                                String productTitle,
                                String productTitleValue,
                                String imageLink,
                                String imageLinkValue,
                                String price,
                                String priceValue,
                                String vendor,
                                String vendorValue) {
        HashMap<String,AttributeValue> keyToGet = new HashMap<>();
        keyToGet.put(key, AttributeValue.builder().s(keyVal).build());
        keyToGet.put(productLink, AttributeValue.builder().s(productLinkValue).build());

        GetItemRequest request = GetItemRequest.builder()
                .key(keyToGet)
                .tableName(tableName)
                .build();

        try {
            Map<String,AttributeValue> returnedItem = ddb.getItem(request).item();

            if (returnedItem != null) {
                Set<String> keys = returnedItem.keySet();
                System.out.println("Amazon DynamoDB table attributes: \n");

                for (String key1 : keys) {
                    System.out.format("%s: %s\n", key1, returnedItem.get(key1).toString());
                }
            } else {
                System.out.format("No item found with the key %s!\n", key);
            }
        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public List<Map<String, AttributeValue>> queryTable(String tableName,
                          String partitionKeyName,
                          String partitionKeyVal,
                          String partitionAlias) {

        // Set up an alias for the partition key name in case it's a reserved word.
        HashMap<String,String> attrNameAlias = new HashMap<String,String>();
        attrNameAlias.put(partitionAlias, partitionKeyName);

        // Set up mapping of the partition name with the value.
        HashMap<String, AttributeValue> attrValues = new HashMap<>();

        attrValues.put(":"+partitionKeyName, AttributeValue.builder()
                .s(partitionKeyVal)
                .build());

        QueryRequest queryReq = QueryRequest.builder()
                .tableName(tableName)
                .keyConditionExpression(partitionAlias + " = :" + partitionKeyName)
                .expressionAttributeNames(attrNameAlias)
                .expressionAttributeValues(attrValues)
                .build();

        try {
            QueryResponse response = ddb.query(queryReq);
            return response.items();
        } catch (DynamoDbException e) {
            // System.err.println(e.getMessage());
            System.exit(1);
        }
        return null;
    }
}
