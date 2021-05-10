//package com.github.DieselNiu.integration;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestPropertySource(locations = "classpath:test.properties")
//public class MyIntegrationTest {
//    @Inject
//    Environment environment;
//
//    @Test
//    public void notLoggedInByDefault() throws IOException {
//        String port = environment.getProperty("local.server.port");
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        try {
//            HttpGet httpget = new HttpGet("http://localhost:" + port + "/auth");
//            httpclient.execute(httpget, (ResponseHandler<String>) httpResponse -> {
//                Assertions.assertEquals(200, httpResponse.getStatusLine().getStatusCode());
//                Assertions.assertTrue(EntityUtils.toString(httpResponse.getEntity()).contains("用户没有登录"));
//                return null;
//            });
//        } finally {
//            httpclient.close();
//        }
//    }
//}
//}
