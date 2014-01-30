package com.wikia.webdriver.Common.Templates;


import com.wikia.webdriver.Common.Core.Annotations.UserAgent;
import com.wikia.webdriver.Common.Core.GeoEdge.GeoEdgeProxy;
import com.wikia.webdriver.Common.Core.GeoEdge.GeoEdgeProxyServer;
import java.lang.reflect.Method;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class AdsTestTemplate extends NewTestTemplate {

	private static DesiredCapabilities adCap;
	private static GeoEdgeProxyServer adServer;
	private boolean isGeoEdgeSet = true;

	/**
	 * Start browser with configured desired capabilities and start logging
	 *
	 * @param Method method
	 */
	@BeforeMethod(alwaysRun=true)
	@Override
	public void start(Method method, Object[] data) {
		try {
			if (method.getAnnotation(GeoEdgeProxy.class) != null) {
				GeoEdgeProxy country = method.getAnnotation(GeoEdgeProxy.class);
				adServer = new GeoEdgeProxyServer(
					country.country(), 4444
				);
				adServer.runGeoEdgeServer();
				adCap = getCapsWithProxyServerSet(adServer);
				setDriverCapabilities(adCap);
			} else {
				isGeoEdgeSet = false;
			} if (method.getAnnotation(UserAgent.class) != null) {
				startBrowserWithModifiedUserAgent(method.getAnnotation(UserAgent.class).userAgent());
			} else {
				startBrowser();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@AfterMethod(alwaysRun=true)
	public void stopServer() {
		if (isGeoEdgeSet) {
			try {
				adServer.stop();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}