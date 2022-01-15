package guldilin.util;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.MyDataCenterInstanceConfig;
import com.netflix.appinfo.providers.EurekaConfigBasedInstanceInfoProvider;
import com.netflix.discovery.DefaultEurekaClientConfig;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.EurekaClientConfig;

public class ServiceDiscoveryClientFactory {

    private static ApplicationInfoManager applicationInfoManager;
    private static EurekaClient eurekaClient;

    private static ApplicationInfoManager initEurekaApplicationInfoManager() {
        if (applicationInfoManager != null) return applicationInfoManager;
        InstanceInfo instanceInfo = new EurekaConfigBasedInstanceInfoProvider(new MyDataCenterInstanceConfig()).get();
        applicationInfoManager = new ApplicationInfoManager(new MyDataCenterInstanceConfig(), instanceInfo);
        return applicationInfoManager;
    }

    private static EurekaClient initEurekaClient() {
        if (eurekaClient != null) return eurekaClient;
        EurekaClientConfig clientConfig = new DefaultEurekaClientConfig();
        eurekaClient = new DiscoveryClient(initEurekaApplicationInfoManager(), clientConfig);
        return eurekaClient;
    }

    public static String getStorageApiUrl() {
        String vipAddress = "zuul-server";

        InstanceInfo nextServerInfo = null;
        try {
            nextServerInfo = initEurekaClient().getNextServerFromEureka(vipAddress, false);
        } catch (Exception e) {
            System.out.println("Cannot get an instance of example service to talk to from eureka");
            throw new RuntimeException(e);
        }
        return nextServerInfo.getVIPAddress() + ":" + nextServerInfo.getPort() + "/storage-api";
    }
}
