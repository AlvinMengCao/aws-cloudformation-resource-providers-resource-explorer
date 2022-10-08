package software.amazon.resourceexplorer2.index;

import software.amazon.awssdk.services.resourceexplorer.ResourceExplorerClient;

import com.amazonaws.util.StringUtils;

import software.amazon.awssdk.regions.Region;
import java.net.URI;


public class ClientFactory {

    private static final String AWS_REGION = "AWS_REGION";
    private static final String DEFAULT_AWS_REGION = "us-west-2";

    private static ResourceExplorerClient client;

    public static ResourceExplorerClient getClient() {

        if(client == null) {
            client = ResourceExplorerClient.builder()
                    .endpointOverride(URI.create(String.format("https://resource-explorer-2.%s.api.aws", getRegion())))
                    .region(Region.of(getRegion()))
                    .build();
        }

        return client;
    }

    private static String getRegion() {
        final String envValue = System.getenv(AWS_REGION);
        return StringUtils.isNullOrEmpty(envValue)? DEFAULT_AWS_REGION: envValue;
    }
}
