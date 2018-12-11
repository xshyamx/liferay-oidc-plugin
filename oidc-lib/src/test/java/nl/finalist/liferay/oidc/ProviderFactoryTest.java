package nl.finalist.liferay.oidc;

import org.junit.Test;

import nl.finalist.liferay.oidc.providers.AzureAD;
import nl.finalist.liferay.oidc.providers.UserInfoProvider;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class ProviderFactoryTest {

	@Parameters(name = "{0}")
	public static Iterable<Object[]> params() {
		return Arrays.asList(new Object[][]{
				{"none", "none", UserInfoProvider.class},
				{"generic match", "GENERIC", UserInfoProvider.class},
				{"empty", "", UserInfoProvider.class},
				{"azure case unmatched", "azure", AzureAD.class},
				{"azure match", "AZURE", AzureAD.class},
		});
	}
	private String name;
	private String providerType;
	private Class<?> expected;

	public ProviderFactoryTest(String name, String providerType, Class<?> expected) {
		this.name = name;
		this.providerType = providerType;
		this.expected = expected;
	}

	@Test
	public void test() {
		UserInfoProvider openIdProvider = ProviderFactory.getOpenIdProvider(providerType);
		assertTrue(expected.isAssignableFrom(openIdProvider.getClass()));
	}
	
}