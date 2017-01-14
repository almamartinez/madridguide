package io.keepcoding.madridguide;

import android.test.AndroidTestCase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import io.keepcoding.madridguide.manager.net.ShopEntity;
import io.keepcoding.madridguide.manager.net.ShopResponse;
import io.keepcoding.madridguide.model.Shop;
import io.keepcoding.madridguide.model.mappers.ShopEntityShopMapper;

public class ShopEntityShopMapperTests extends AndroidTestCase {

    private static final String TEST_STRING = "{\n" +
            "  \"result\": [\n" +
            "    {\n" +
            "      \"id\": \"310\",\n" +
            "      \"name\": \"acosta\",\n" +
            "      \"img\": \"http:\\/\\/madrid-shops.com\\/media\\/shops\\/acosta-small.jpg\",\n" +
            "      \"logo_img\": \"http:\\/\\/madrid-shops.com\\/media\\/shops\\/logo-acosta-200.jpg\",\n" +
            "      \"address\": \"Calle de Claudio Coello, 46, 28001 Madrid\",\n" +
            "      \"opening_hours_es\": \"L-S 10:30 - 20:30\",\n" +
            "      \"opening_hours_en\": \"M-S 10:30 - 20:30\",\n" +
            "      \"opening_hours_cn\": \"10:30 - 20:30\",\n" +
            "      \"opening_hours_jp\": \" \\u6708\\u66dc\\uff5e\\u571f\\u66dc10:30-20:30\",\n" +
            "      \"opening_hours_mx\": \"L-S 10:30 - 20:30\",\n" +
            "      \"opening_hours_cl\": \"L-S 10:30 - 20:30\",\n" +
            "      \"telephone\": \"914 35 76 57\",\n" +
            "      \"email\": \"\",\n" +
            "      \"url\": \"http:\\/\\/acostamadrid.com\\/es\\/\",\n" +
            "      \"description_jp\": \"Acosta\\u306f\\u30d0\\u30c3\\u30b0\\u3084\\u9769\\u88fd\\u54c1\\u306e\\u30d6\\u30e9\\u30f3\\u30c9\\u3067\\u3001\\u201cMade in Spain\\u201d\\u3092\\u30b3\\u30f3\\u30bb\\u30d7\\u30c8\\u306b\\u3057\\u3066\\u3044\\u307e\\u3059\\u30021942\\u5e74\\u304b\\u3089\\u30b9\\u30da\\u30a4\\u30f3\\u306e\\u4f1d\\u7d71\\u3084\\u54c1\\u8cea\\u3092\\u91cd\\u3093\\u3058\\u3001\\u30c7\\u30b6\\u30a4\\u30f3\\u304b\\u3089\\u88fd\\u54c1\\u306e\\u88fd\\u9020\\u3001\\u307e\\u305f\\u682a\\u4e3b\\u307e\\u3067\\u3082\\u304c\\u30b9\\u30da\\u30a4\\u30f3\\u3067\\u3059\\u3002\\u76ae\\u9769\\u306e\\u5927\\u5207\\u306a\\u70b9\\u306f\\u305d\\u306e\\u6e90\\u3067\\u3001\\u6d0b\\u670d\\u3084\\u9774\\u3001\\u30d9\\u30eb\\u30c8\\u7b49\\u5c0f\\u3055\\u306a\\u5546\\u54c1\\u3082\\u758e\\u304b\\u306b\\u3059\\u308b\\u3053\\u3068\\u306a\\u304f\\u3001\\u30d0\\u30c3\\u30b0\\u3084\\u76ae\\u9769\\u88fd\\u54c1\\u3001\\u5168\\u3066\\u306e\\u30b3\\u30ec\\u30af\\u30b7\\u30e7\\u30f3\\u306bAcosta\\u304c\\u4f7f\\u7528\\u3059\\u308b\\u76ae\\u9769\\u306f\\u5e38\\u306b\\u6700\\u9ad8\\u7d1a\\u306e\\u54c1\\u8cea\\u3067\\u3001\\u56fd\\u5185\\u30fb\\u30e8\\u30fc\\u30ed\\u30c3\\u30d1\\u306e\\u306a\\u3081\\u3057\\u9769\\u5de5\\u5834\\u3068\\u76f4\\u63a5\\u53d6\\u5f15\\u3092\\u3057\\u3066\\u3044\\u307e\\u3059\\u3002\\u307e\\u305f\\u3001\\u305d\\u308c\\u305e\\u308c\\u306e\\u5546\\u54c1\\u306f\\u624b\\u4ed5\\u4e8b\\u3067\\u884c\\u3063\\u3066\\u304a\\u308a\\u3001Acosta\\u304c\\u7279\\u5225\\u3067\\u3042\\u308b\\u5074\\u9762\\u3092\\u7279\\u5fb4\\u4ed8\\u3051\\u3066\\u3044\\u307e\\u3059\\u3002\\u719f\\u7df4\\u3057\\u305f\\u8077\\u4eba\\u304c\\u3053\\u306e\\u30d6\\u30e9\\u30f3\\u30c9\\u306e\\u305f\\u3081\\u306b\\u5546\\u54c1\\u3092\\u4f5c\\u308a\\u3001\\u305d\\u306e\\u3046\\u3061\\u306e\\u6570\\u4eba\\u306f\\u4ee3\\u3005\\u4e00\\u7dd2\\u306b\\u4ed5\\u4e8b\\u3092\\u3057\\u3066\\u304a\\u308a\\u3001\\u76ae\\u3092\\u9078\\u3073\\u3001\\u30d0\\u30c3\\u30af\\u4f5c\\u308a\\u306b\\u4f7f\\u7528\\u3059\\u308b\\u90e8\\u54c1\\u307e\\u3067\\u3082\\u7d30\\u5fc3\\u306e\\u6ce8\\u610f\\u3092\\u6255\\u3063\\u3066\\u3044\\u307e\\u3059\\u3002\\u305d\\u306e\\u70ba\\u304a\\u5ba2\\u69d8\\u304b\\u3089\\u306e\\u4f9d\\u983c\\u3067\\u4e16\\u754c\\u3067\\u305f\\u3060\\u4e00\\u3064\\u306e\\u5546\\u54c1\\u3082\\u4f5c\\u6210\\u3067\\u304d\\u307e\\u3059\\u3002Acosta\\u306e\\u5168\\u3066\\u306e\\u30b3\\u30ec\\u30af\\u30b7\\u30e7\\u30f3\\u306f\\u5b63\\u7bc0\\u5916\\u308c\\u306b\\u6d6e\\u304b\\u3093\\u3060\\u8f1d\\u304b\\u3057\\u3044\\u30a2\\u30a4\\u30c7\\u30a2\\u306b\\u57fa\\u3065\\u3044\\u3066\\u3044\\u307e\\u3059\\u3002\\u30a4\\u30f3\\u30b9\\u30d4\\u30ec\\uff0d\\u30b7\\u30e7\\u30f3\\u306f\\u90fd\\u4f1a\\u7684\\u3067\\u5b9f\\u8df5\\u7684\\u3001\\u4e0a\\u54c1\\u3067\\u56fd\\u969b\\u7684\\u306a\\u5973\\u6027\\u304b\\u3089\\u751f\\u307e\\u308c\\u307e\\u3059\\u3002\\u5b9f\\u7528\\u7684\\u3067\\u3042\\u308b\\u4e8b\\u3092\\u8ae6\\u3081\\u305a\\u62d8\\u3089\\u306a\\u3044\\u3001\\u3053\\u306e\\u3088\\u3046\\u306a21\\u4e16\\u7d00\\u306e\\u30b9\\u30da\\u30a4\\u30f3\\u5973\\u6027\\u3092\\u53cd\\u6620\\u3057\\u3001(\\u5b9f\\u969b\\u306b\\u5b58\\u5728\\u3057\\u307e\\u3059)\\u3001\\u30ab\\u30eb\\u30e1\\u30f3\\u3084\\u30c6\\u30ec\\u30b5\\u3092\\u8c61\\u5fb4\\u3059\\u308b\\u30d0\\u30c3\\u30b0\\u306b\\u306a\\u308a\\u307e\\u3059\\u3002\\u30a4\\u30f3\\u30b9\\u30d4\\u30ec\\uff0d\\u30b7\\u30e7\\u30f3\\u306fAcosta\\u306e\\u30b3\\u30ec\\u30af\\u30b7\\u30e7\\u30f3\\u3054\\u3068\\u306b\\u66f4\\u65b0\\u3055\\u308c\\u3001\\u6211\\u3005\\u306e\\u30b7\\u30f3\\u30dc\\u30eb\\u3068\\u878d\\u5408\\u3057\\u307e\\u3059\\u3002\",\n" +
            "      \"description_mx\": \"Acosta es una marca de bolsos y art\\u00edculos de piel que vive y se expresa\\r\\ncon el concepto \\u201cMade in Spain\\u201d. Desde el dise\\u00f1o y la fabricaci\\u00f3n de los\\r\\nart\\u00edculos hasta los mismos accionistas de la sociedad, la marca respira\\r\\ntradici\\u00f3n, artesan\\u00eda y calidad espa\\u00f1olas, desde 1.942.\\r\\n\\r\\nLa importancia de la piel es la se\\u00f1a de identidad, presente en toda la\\r\\ncolecci\\u00f3n y aplicada en bolsos y peque\\u00f1a marroquiner\\u00eda, sin descuidar\\r\\notros complementos de mujer como prendas, zapatos y cinturones.\\r\\n\\r\\nEl origen de las pieles Acosta es siempre de primera calidad, gracias al\\r\\nacceso directo a las mejores tener\\u00edas nacionales y europeas.\\r\\n\\r\\nLa artesan\\u00eda es otra nota exclusiva de la personalidad Acosta. Cada\\r\\nproducto se fabrica manualmente. Artesanos expertos trabajan para la\\r\\nmarca, algunos desde hace generaciones, seleccionando las pieles y\\r\\ncuidando al detalle cada elemento que conformar\\u00e1 el bolso.\\r\\n\\r\\nEsta forma de trabajar nos permite incluso realizar piezas \\u00fanicas, a\\r\\npetici\\u00f3n del cliente.\\r\\n\\r\\nTodas las colecciones de Acosta responden a una idea del lujo fuera del\\r\\ntiempo. La inspiraci\\u00f3n nace de la mujer urbana, pr\\u00e1ctica, elegante y\\r\\ncosmopolita.\\r\\n\\r\\nUna mujer del siglo XXI que no renuncia a la practicidad, pero tampoco al\\r\\ndise\\u00f1o. Reflejo de esta mujer espa\\u00f1ola, plenamente actual, son los bolsos\\r\\nemblem\\u00e1ticos de la marca, Carmen y Teresa.\\r\\n\\r\\nY la inspiraci\\u00f3n se renueva en cada colecci\\u00f3n Acosta, a la que se unir\\u00e1n\\r\\nnuevos iconos.\",\n" +
            "      \"description_cl\": \"Acosta es una marca de bolsos y art\\u00edculos de piel que vive y se expresa\\r\\ncon el concepto \\u201cMade in Spain\\u201d. Desde el dise\\u00f1o y la fabricaci\\u00f3n de los\\r\\nart\\u00edculos hasta los mismos accionistas de la sociedad, la marca respira\\r\\ntradici\\u00f3n, artesan\\u00eda y calidad espa\\u00f1olas, desde 1.942.\\r\\n\\r\\nLa importancia de la piel es la se\\u00f1a de identidad, presente en toda la\\r\\ncolecci\\u00f3n y aplicada en bolsos y peque\\u00f1a marroquiner\\u00eda, sin descuidar\\r\\notros complementos de mujer como prendas, zapatos y cinturones.\\r\\n\\r\\nEl origen de las pieles Acosta es siempre de primera calidad, gracias al\\r\\nacceso directo a las mejores tener\\u00edas nacionales y europeas.\\r\\n\\r\\nLa artesan\\u00eda es otra nota exclusiva de la personalidad Acosta. Cada\\r\\nproducto se fabrica manualmente. Artesanos expertos trabajan para la\\r\\nmarca, algunos desde hace generaciones, seleccionando las pieles y\\r\\ncuidando al detalle cada elemento que conformar\\u00e1 el bolso.\\r\\n\\r\\nEsta forma de trabajar nos permite incluso realizar piezas \\u00fanicas, a\\r\\npetici\\u00f3n del cliente.\\r\\n\\r\\nTodas las colecciones de Acosta responden a una idea del lujo fuera del\\r\\ntiempo. La inspiraci\\u00f3n nace de la mujer urbana, pr\\u00e1ctica, elegante y\\r\\ncosmopolita.\\r\\n\\r\\nUna mujer del siglo XXI que no renuncia a la practicidad, pero tampoco al\\r\\ndise\\u00f1o. Reflejo de esta mujer espa\\u00f1ola, plenamente actual, son los bolsos\\r\\nemblem\\u00e1ticos de la marca, Carmen y Teresa.\\r\\n\\r\\nY la inspiraci\\u00f3n se renueva en cada colecci\\u00f3n Acosta, a la que se unir\\u00e1n\\r\\nnuevos iconos.\",\n" +
            "      \"description_cn\": \"ACOSTA\\u662f\\u4e00\\u5bb6\\u8457\\u540d\\u7684\\u897f\\u73ed\\u7259\\u76ae\\u5177\\u54c1\\u724c.\\u81ea1942\\u5e74\\u521b\\u7acb\\u4e4b\\u65e5\\u8d77,\\u8be5\\u54c1\\u724c\\u76ae\\u5177\\u4e00\\u76f4\\u5949\\u884c\\u5ef6\\u7eed\\u4f20\\u7edf\\u3001\\u575a\\u6301\\u624b\\u5de5\\u53ca\\u8d28\\u91cf\\u7b2c\\u4e00\\u4e4b\\u5b97\\u65e8\\uff0c\\u7528\\u5fc3\\u4e3a\\u5ba2\\u6237\\u6253\\u9020\\u7cbe\\u54c1\\u76ae\\u5305\\u3001\\u76ae\\u5177\\u3001\\u76ae\\u8d28\\u8863\\u7269\\u3001\\u76ae\\u978b\\u53ca\\u76ae\\u5e26\\u7b49\\u7269\\u54c1\\u3002\\r\\nACOSTA\\u76ae\\u5177\\u59cb\\u7ec8\\u628a\\u8d28\\u91cf\\u653e\\u5728\\u7b2c\\u4e00\\uff0c\\u8fd9\\u8fd8\\u5f97\\u5f97\\u76ca\\u4e8e\\u4ed6\\u4eec\\u62e5\\u6709\\u5168\\u897f\\u73ed\\u7259\\u4e43\\u81f3\\u5168\\u6b27\\u6d32\\u6700\\u597d\\u7684\\u5236\\u9769\\u5382\\u3002\\r\\n\\u9664\\u6b64\\u4e4b\\u5916\\uff0c\\u7eaf\\u624b\\u5de5\\u5236\\u4f5c\\u4e5f\\u662f\\u8be5\\u54c1\\u724c\\u7684\\u53e6\\u4e00\\u5927\\u7279\\u8272\\u3002AOSTA\\u65d7\\u4e0b\\u7684\\u6240\\u6709\\u76ae\\u9769\\u5236\\u54c1\\u5747\\u662f\\u7eaf\\u624b\\u5de5\\u6253\\u9020\\uff0c\\u4e00\\u5927\\u6279\\u4f18\\u79c0\\u7684\\u76ae\\u9769\\u4e13\\u5bb6\\u4ece\\u9009\\u6750\\u3001\\u8bbe\\u8ba1\\u5230\\u751f\\u4ea7\\u52a0\\u5de5\\u4e3a\\u987e\\u5ba2\\u4e25\\u683c\\u628a\\u5173\\u3002\\r\\n\\u7cbe\\u7f8e\\u7684\\u8bbe\\u8ba1\\u52a0\\u4e0a\\u4e25\\u8c28\\u7684\\u5236\\u4f5c\\u6d41\\u7a0b,\\u4f7fACOSTA\\u80fd\\u751f\\u4ea7\\u51fa\\u72ec\\u4e00\\u65e0\\u4e8c\\u7684\\u76ae\\u5177\\u7528\\u54c1,\\u4ee5\\u53ca\\u5177\\u5907\\u4e86\\u4e3a\\u5ba2\\u6237\\u8ba2\\u5236\\u4e13\\u5c5e\\u4ea7\\u54c1\\u7684\\u5b9e\\u529b\\u3002ACOSTA\\u6240\\u6709\\u7684\\u7cfb\\u5217\\u90fd\\u914d\\u5f97\\u4e0a\\u5962\\u534e\\u4e8c\\u5b57\\uff0c\\u5176\\u7075\\u611f\\u5747\\u6765\\u81ea\\u4e8e\\u65e5\\u5e38\\u751f\\u6d3b\\u4e2d\\u7684\\u90fd\\u5e02\\u5973\\u6027\\u3001\\u804c\\u4e1a\\u5973\\u6027\\u3001\\u4f18\\u96c5\\u5973\\u6027\\u53ca\\u5177\\u6709\\u56fd\\u9645\\u89c6\\u91ce\\u7684\\u5973\\u6027\\u3002\\r\\n\\u5c06\\u65f6\\u5c1a\\u7684\\u7075\\u611f\\u4e0e\\u7bb1\\u5305\\u4e3a\\u4eba\\u4eec\\u51fa\\u884c\\u5e26\\u6765\\u7684\\u4fbf\\u5229\\u76f8\\u7ed3\\u5408\\uff0c\\u8fd9\\u6b63\\u662fACOSTA\\u7684\\u4e00\\u5927\\u6807\\u5fd7\\u3002\\r\\n\",\n" +
            "      \"description_en\": \"Acosta is a brand of handbags and leather goods that  is a living and expression\\r\\nof the \\\\\\\"Made in Spain\\\\\\\" concept. Since the design the manufacture and \\r\\neven the  shareholders of the company are the same, the brand breathes\\r\\ntradition, quality craftsmanship and  is typically  Spanish, dating from 1,942.\\r\\n\\r\\nThe importance of the leather is the hallmark, and is present throughout the\\r\\ncollection and is present  in bags and small leather goods, not forgetting\\r\\nother accessories for women such as clothes, shoes and belts.\\r\\n\\r\\nThe origin of the fine leather used by  Acosta is always top quality, thanks to\\r\\ndirect access to the best national and European tanneries.\\r\\n\\r\\nCraftsmanship is another exclusive note in the  Acosta personality. Each\\r\\nproduct is made by hand. Skilled craftsmen working for the\\r\\nbrand, some for generations, choose the skins and\\r\\ntaking care with the  details of every element that makes up the bag.\\r\\n\\r\\nThis way of working allows us even to make unique pieces,\\r\\nat the request of the client.\\r\\n\\r\\nAll collections Acosta respond to an idea of timeless luxury . Our inspiration comes from the urban, practical, elegant, cosmopolitan woman.\\r\\n\\r\\nA woman of  the twenty-first century enjoys  convenience without  losing sight of the idea of  design. This is reflected in our modern designs for  Spanish women in the iconic brands of  bags, Carmen and Teresa.\\r\\n\\r\\nThe  inspiration is present  in  every new Acosta collection, giving us still more\\r\\nnew icons.\\r\\n\",\n" +
            "      \"description_es\": \"Acosta es una marca de bolsos y art\\u00edculos de piel que vive y se expresa\\r\\ncon el concepto \\u201cMade in Spain\\u201d. Desde el dise\\u00f1o y la fabricaci\\u00f3n de los\\r\\nart\\u00edculos hasta los mismos accionistas de la sociedad, la marca respira\\r\\ntradici\\u00f3n, artesan\\u00eda y calidad espa\\u00f1olas, desde 1.942.\\r\\n\\r\\nLa importancia de la piel es la se\\u00f1a de identidad, presente en toda la\\r\\ncolecci\\u00f3n y aplicada en bolsos y peque\\u00f1a marroquiner\\u00eda, sin descuidar\\r\\notros complementos de mujer como prendas, zapatos y cinturones.\\r\\n\\r\\nEl origen de las pieles Acosta es siempre de primera calidad, gracias al\\r\\nacceso directo a las mejores tener\\u00edas nacionales y europeas.\\r\\n\\r\\nLa artesan\\u00eda es otra nota exclusiva de la personalidad Acosta. Cada\\r\\nproducto se fabrica manualmente. Artesanos expertos trabajan para la\\r\\nmarca, algunos desde hace generaciones, seleccionando las pieles y\\r\\ncuidando al detalle cada elemento que conformar\\u00e1 el bolso.\\r\\n\\r\\nEsta forma de trabajar nos permite incluso realizar piezas \\u00fanicas, a\\r\\npetici\\u00f3n del cliente.\\r\\n\\r\\nTodas las colecciones de Acosta responden a una idea del lujo fuera del\\r\\ntiempo. La inspiraci\\u00f3n nace de la mujer urbana, pr\\u00e1ctica, elegante y\\r\\ncosmopolita.\\r\\n\\r\\nUna mujer del siglo XXI que no renuncia a la practicidad, pero tampoco al\\r\\ndise\\u00f1o. Reflejo de esta mujer espa\\u00f1ola, plenamente actual, son los bolsos\\r\\nemblem\\u00e1ticos de la marca, Carmen y Teresa.\\r\\n\\r\\nY la inspiraci\\u00f3n se renueva en cada colecci\\u00f3n Acosta, a la que se unir\\u00e1n\\r\\nnuevos iconos.\",\n" +
            "      \"gps_lat\": \"40.4268612\",\n" +
            "      \"gps_lon\": \"-3.686238000000003\",\n" +
            "      \"special_offer\": \"false\",\n" +
            "      \"categories\": [\n" +
            "        \"1\"\n" +
            "      ],\n" +
            "      \"keywords_es\": \"Acosta, Bolsos\",\n" +
            "      \"keywords_en\": \"Acosta, Bags\",\n" +
            "      \"keywords_cn\": \"\\u5305\\u88c5\\u888b\",\n" +
            "      \"keywords_jp\": \"Acosta, \\u30d0\\u30c3\\u30b0\",\n" +
            "      \"keywords_mx\": \"Acosta, Bolsos\",\n" +
            "      \"keywords_cl\": \"Acosta, Bolsos\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"47\",\n" +
            "      \"name\": \"Adolfo Dominguez\",\n" +
            "      \"img\": \"http:\\/\\/madrid-shops.com\\/media\\/shops\\/adolfo-small.jpg\",\n" +
            "      \"logo_img\": \"http:\\/\\/madrid-shops.com\\/media\\/shops\\/logo-adolfo-200.jpg\",\n" +
            "      \"address\": \"Calle Serrano, 96\",\n" +
            "      \"opening_hours_es\": \"Lun-Sab \\u00b7 11:00\\u201321:00\",\n" +
            "      \"opening_hours_en\": \"Monday to Saturday: 11: 00-21: 00\",\n" +
            "      \"opening_hours_cn\": \"\\u5468\\u4e8c - \\u5468\\u516d\\uff1a11:00-21:00\",\n" +
            "      \"opening_hours_jp\": \" \\u6708\\u66dc\\uff5e\\u571f\\u66dc11:00-21:00\",\n" +
            "      \"opening_hours_mx\": \"Lun-Sab \\u00b7 11:00\\u201321:00\",\n" +
            "      \"opening_hours_cl\": \"Lun-Sab \\u00b7 11:00\\u201321:00\",\n" +
            "      \"telephone\": \"(34) 91 576 70 53\",\n" +
            "      \"email\": \"\",\n" +
            "      \"url\": \"http:\\/\\/www.adolfodominguez.com\",\n" +
            "      \"description_jp\": \"Adolfo Dominguez\\u306f\\u30de\\u30c9\\u30ea\\uff0d\\u30c9\\u306e\\u30b4\\uff0d\\u30eb\\u30c7\\u30f3\\u30fb\\u30de\\u30a4\\u30eb\\u306b\\u3042\\u308a\\u3001\\u5efa\\u7269\\u5168\\u90e8\\u3092\\u5360\\u3081\\u308b\\u3053\\u306e\\u30c7\\u30b6\\u30a4\\u30ca\\uff0d\\u306e\\u30e1\\u30a4\\u30f3\\u306e\\u5e97\\u8217\\u306f\\u30d5\\u30a1\\u30c3\\u30b7\\u30e7\\u30f3\\u306e\\u8056\\u5730\\u3068\\u3057\\u3066\\u77e5\\u3089\\u308c\\u3066\\u3044\\u307e\\u3059\\u3002\\u3053\\u306e\\u5e97\\u8217\\u306b\\u306f\\u30ec\\u30c7\\u30a3\\u30fc\\u30b9\\u3001\\u30e1\\u30f3\\u30ba\\u306e\\u5168\\u30b3\\u30ec\\u30af\\u30b7\\u30e7\\u30f3\\u3001\\u30a6\\u30a8\\u30c7\\u30a3\\u30f3\\u30b0\\u30c9\\u30ec\\u30b9\\u306e\\u7279\\u5225\\u30b3\\uff0d\\u30ca\\uff0d\\u3001\\u88c5\\u98fe\\u54c1\\u3001\\u597d\\u5947\\u5fc3\\u305d\\u305d\\u308b\\u30a2\\u30a4\\u30c6\\u30e0\\u3001\\u30de\\u30b9\\u30b3\\u30c3\\u30c8\\u7528\\u5546\\u54c1\\u304c\\u3042\\u308a\\u307e\\u3059\\u3002\\u6700\\u4e0a\\u968e\\u306b\\u306f\\u3053\\u306e\\u30d6\\u30e9\\u30f3\\u30c9\\u306e\\u30ab\\u30d5\\u30a7\\u3082\\u3042\\u308a\\u3001\\u30b7\\u30e7\\u30c3\\u30d4\\u30f3\\u30b0\\u306e\\u5408\\u9593\\u306b\\u5feb\\u9069\\u306b\\u4f11\\u61a9\\u3067\\u304d\\u307e\\u3059\\u3002\",\n" +
            "      \"description_mx\": \"La tienda del creador Adolfo Dominguez en la milla del lujo madrile\\u00f1a, puede ser considerada como un santuario de la moda, ya que ocupa un edificio completo y es la mayor tienda del dise\\u00f1ador. Nos atrevemos a decir que en ella encontrar\\u00e1s la colecci\\u00f3n completa para ellas y ellos, un exclusivo apartado de ensue\\u00f1o para novias, decoraci\\u00f3n, art\\u00edculos curiosos y un rinc\\u00f3n de prendas para tus mascotas. En la \\u00faltima planta del edificio se encuentra la exclusiva cafeter\\u00eda de la firma, en la que podr\\u00e1s relajarte y amenizar tu d\\u00eda de shopping.\",\n" +
            "      \"description_cl\": \"La tienda del creador Adolfo Dominguez en la milla del lujo madrile\\u00f1a, puede ser considerada como un santuario de la moda, ya que ocupa un edificio completo y es la mayor tienda del dise\\u00f1ador. Nos atrevemos a decir que en ella encontrar\\u00e1s la colecci\\u00f3n completa para ellas y ellos, un exclusivo apartado de ensue\\u00f1o para novias, decoraci\\u00f3n, art\\u00edculos curiosos y un rinc\\u00f3n de prendas para tus mascotas. En la \\u00faltima planta del edificio se encuentra la exclusiva cafeter\\u00eda de la firma, en la que podr\\u00e1s relajarte y amenizar tu d\\u00eda de shopping.\",\n" +
            "      \"description_cn\": \"Adolfo dominguez \\u4e0d\\u4ec5\\u662f\\u9a6c\\u5fb7\\u91cc\\u7684\\u4e00\\u4e2a\\u5962\\u4f88\\u54c1\\u54c1\\u724c\\uff0c\\u800c\\u662f\\u4e00\\u4e2a\\u53d7\\u5230\\u4e16\\u754c\\u8ba4\\u53ef\\u7684\\u65f6\\u5c1a\\u54c1\\u724c\\u3002\\u8fd9\\u4e2aAdolfo dominguez \\u7684\\u5206\\u5e97\\u5360\\u636e\\u4e86\\u9a6c\\u5fb7\\u91cc\\u9ec4\\u91d1\\u5730\\u5e26\\u4e0a\\u7684\\u4e00\\u6574\\u5e62\\u5927\\u697c\\u3002\\u5728\\u8fd9\\u91cc\\u80fd\\u627e\\u5230\\u65f6\\u5c1a\\u7537\\u5973\\u6240\\u9700\\u8981\\u7684\\u5404\\u7c7b\\u4ea7\\u54c1\\uff0c\\u800c\\u4e14\\u51fa\\u4e4e\\u610f\\u6599\\u7684\\u662f\\uff0c\\u8fd9\\u91cc\\u7adf\\u7136\\u8fd8\\u6709\\u5a5a\\u793c\\u7528\\u914d\\u9970\\u548c\\u5ba0\\u7269\\u7528\\u54c1\\u3002\\u6700\\u9ad8\\u5c42\\u5219\\u662f\\u4e00\\u4e2a\\u5496\\u5561\\u9986\\uff0c\\u8d2d\\u7269\\u5b8c\\u4e4b\\u540e\\u4f60\\u53ef\\u4ee5\\u5728\\u90a3\\u91cc\\u559d\\u676f\\u5496\\u5561\\u5c0f\\u61a9\\u4e00\\u4e0b\\u3002\",\n" +
            "      \"description_en\": \"The creator Adolfo Dominguez store in Madrid\\u2019s luxury mile, can be considered as a sanctuary of fashion, occupying an entire building and is the largest designer store. We dare to say that you will find the complete collection for women and men, an exclusive section for brides dream decoration items and  an unusual corner of garments for your pets.  On the top floor is the exclusive cafe, where you can relax and muse over your day of shopping.\\r\\n \\r\\n\",\n" +
            "      \"description_es\": \"La tienda del creador Adolfo Dominguez en la milla del lujo madrile\\u00f1a, puede ser considerada como un santuario de la moda, ya que ocupa un edificio completo y es la mayor tienda del dise\\u00f1ador. Nos atrevemos a decir que en ella encontrar\\u00e1s la colecci\\u00f3n completa para ellas y ellos, un exclusivo apartado de ensue\\u00f1o para novias, decoraci\\u00f3n, art\\u00edculos curiosos y un rinc\\u00f3n de prendas para tus mascotas. En la \\u00faltima planta del edificio se encuentra la exclusiva cafeter\\u00eda de la firma, en la que podr\\u00e1s relajarte y amenizar tu d\\u00eda de shopping.\",\n" +
            "      \"gps_lat\": \"40.4332712    \",\n" +
            "      \"gps_lon\": \"-3.6865179000000126\",\n" +
            "      \"special_offer\": \"false\",\n" +
            "      \"categories\": [\n" +
            "        \"1\"\n" +
            "      ],\n" +
            "      \"keywords_es\": \"Adolfo Dominguez, Moda Mujer, Moda hombre, Complementos mujer, complementos hombre. Moda Novias, moda hogar\",\n" +
            "      \"keywords_en\": \"Adolfo Dominguez, Fashion, Menswear, Women\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\n" +
            "      \"keywords_cn\": \"Adolfo Dominguez\\u65f6\\u88c5\\u5e97\\uff0c\\u5973\\u88c5\\uff0c\\u7537\\u88c5\\uff0c\\u5973\\u914d\\u9970\\uff0c\\u7537\\u914d\\u9970\\uff0c\\u5a5a\\u7eb1\\uff0c\\u5bb6\\u5c45\\u65f6\\u5c1a\\u7528\\u54c1\",\n" +
            "      \"keywords_jp\": \"Adolfo Dominguez, \\u30ec\\u30c7\\u30a3\\u30fc\\u30b9\\u30d5\\u30a1\\u30c3\\u30b7\\u30e7\\u30f3,\\u30e1\\u30f3\\u30ba\\u30d5\\u30a1\\u30c3\\u30b7\\u30e7\\u30f3,\\u30ec\\u30c7\\u30a3\\u30fc\\u30b9\\u30a2\\u30af\\u30bb\\u30b5\\u30ea\\u30fc,\\u30e1\\u30f3\\u30ba\\u30a2\\u30af\\u30bb\\u30b5\\u30ea\\u30fc,\\u30a6\\u30a8\\u30c7\\u30a3\\u30f3\\u30b0\\u30c9\\u30ec\\u30b9,\\u30a4\\u30f3\\u30c6\\u30ea\\u30a2\\u7528\\u54c1\",\n" +
            "      \"keywords_mx\": \"Adolfo Dominguez, Moda Mujer, Moda hombre, Complementos mujer, complementos hombre. Moda Novias, moda hogar\",\n" +
            "      \"keywords_cl\": \"Adolfo Dominguez, Moda Mujer, Moda hombre, Complementos mujer, complementos hombre. Moda Novias, moda hogar\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"24\",\n" +
            "      \"name\": \"Agatha Ruiz de la Prada\",\n" +
            "      \"img\": \"http:\\/\\/madrid-shops.com\\/media\\/shops\\/agatha-small.jpg\",\n" +
            "      \"logo_img\": \"http:\\/\\/madrid-shops.com\\/media\\/shops\\/logo-agatha-200.jpg\",\n" +
            "      \"address\": \"Calle de Serrano, 27\",\n" +
            "      \"opening_hours_es\": \"De lu. a s\\u00e1. de 10  a 20:30 h\",\n" +
            "      \"opening_hours_en\": \"Monday to Saturday: 10:00-20:30h\",\n" +
            "      \"opening_hours_cn\": \"\\u5468\\u4e8c - \\u5468\\u516d\\uff1a10:00-20:30 \",\n" +
            "      \"opening_hours_jp\": \" \\u6708\\u66dc\\uff5e\\u571f\\u66dc10:00-20:30\",\n" +
            "      \"opening_hours_mx\": \"De lu. a s\\u00e1. de 10  a 20:30 h\",\n" +
            "      \"opening_hours_cl\": \"De lu. a s\\u00e1. de 10  a 20:30 h\",\n" +
            "      \"telephone\": \"(+34) 913 190 501\",\n" +
            "      \"email\": \"\",\n" +
            "      \"url\": \"https:\\/\\/www.agatharuizdelaprada.com\\/store\\/\",\n" +
            "      \"description_jp\": \"\\u30ec\\u30c7\\u30a3\\u30fc\\u30b9\\u3068\\u30ad\\u30c3\\u30ba\\u306e\\u670d\\u3092\\u6271\\u3046\\u30de\\u30c9\\u30ea\\uff0d\\u30c9\\u306eAgatha Ruiz de la Prada\\u3067\\u306f\\u3001\\u30c7\\u30b6\\u30a4\\u30ca\\uff0d\\u306e\\u5275\\u9020\\u6027\\u3068\\u30e6\\uff0d\\u30e2\\u30a2\\u304c\\u540c\\u3058\\u7a7a\\u9593\\u306b\\u307e\\u3068\\u307e\\u3063\\u3066\\u3044\\u307e\\u3059\\u3002\\u305d\\u306e\\u4ed6\\u306b\\u3082\\u697d\\u3057\\u3044\\u30b9\\u30bf\\u30a4\\u30eb\\u306e\\u30d0\\u30c3\\u30b0\\u30fb\\u6c34\\u7740\\u30fb\\u9774\\u30fb\\u30a2\\u30af\\u30bb\\u30b5\\u30ea\\u30fc\\u30fb\\u30b5\\u30f3\\u30b0\\u30e9\\u30b9\\u3082\\u3042\\u308a\\u307e\\u3059\\u3002\",\n" +
            "      \"description_mx\": \"La tienda de Agatha Ruiz de la Prada en Madrid con prendas para  mujer y ni\\u00f1o re\\u00fane en un mismo espacio la creatividad y humor de los que ha hecho bandera la dise\\u00f1adora. Cuenta  adem\\u00e1s con divertidas l\\u00edneas de bolsas, ropa de ba\\u00f1o, zapatos, JOYER\\u00cdA o lentes de sol.\",\n" +
            "      \"description_cl\": \"La tienda de Agatha Ruiz de la Prada en Madrid con prendas para  mujer y ni\\u00f1o re\\u00fane en un mismo espacio la creatividad y humor de los que ha hecho bandera la dise\\u00f1adora. Cuenta  adem\\u00e1s con divertidas l\\u00edneas de bolsos, ropa de ba\\u00f1o, zapatos, JOYER\\u00cdA o gafas de sol.\",\n" +
            "      \"description_cn\": \"Agatha Ruiz de la Prada\\u5728\\u9a6c\\u5fb7\\u91cc\\u7684\\u5206\\u5e97\\u91cc\\u4e13\\u8425\\u5973\\u88c5\\u53ca\\u7ae5\\u88c5\\uff0c\\u4ea7\\u54c1\\u98ce\\u683c\\u524d\\u536b\\u53c8\\u6d3b\\u6cfc\\u3002\\u9500\\u552e\\u624b\\u888b\\u3001\\u6cf3\\u8863\\u3001\\u978b\\u5b50\\u3001\\u73e0\\u5b9d\\u53ca\\u592a\\u9633\\u773c\\u955c\\u7b49\\u591a\\u7ebf\\u4ea7\\u54c1\\u3002\",\n" +
            "      \"description_en\": \"The shop   Agatha Ruiz de la Prada in Madrid has garments  for  woman and children   and  in the same space shows the  creativity and humor that has made this one of the leading designer.  We also encounter fun lines in handbags, swimwear, shoes jewelry and  sunglasses. \",\n" +
            "      \"description_es\": \"La tienda de Agatha Ruiz de la Prada en Madrid con prendas para  mujer y ni\\u00f1o re\\u00fane en un mismo espacio la creatividad y humor de los que ha hecho bandera la dise\\u00f1adora. Cuenta  adem\\u00e1s con divertidas l\\u00edneas de bolsos, ropa de ba\\u00f1o, zapatos, JOYER\\u00cdA o gafas de sol.\",\n" +
            "      \"gps_lat\": \"40.426905\",\n" +
            "      \"gps_lon\": \"-3.687887499999988\",\n" +
            "      \"special_offer\": \"false\",\n" +
            "      \"categories\": [\n" +
            "        \"1\"\n" +
            "      ],\n" +
            "      \"keywords_es\": \"agatha ruiz de la prada, moda mujer, moda hombre, moda infantil, complementos, zapatos.\",\n" +
            "      \"keywords_en\": \"Agatha Ruiz de la Prada, women\\u2019s fashion, men\\u2019s fashion, children\\\\'s fashion, accessories, shoes.\",\n" +
            "      \"keywords_cn\": \"agatha ruiz de la prada\\uff0c\\u5973\\u88c5\\uff0c\\u7537\\u88c5\\uff0c\\u7ae5\\u88c5\\uff0c\\u914d\\u9970\\uff0c\\u978b\\u5b50\",\n" +
            "      \"keywords_jp\": \"agatha ruiz de la prada, \\u30ec\\u30c7\\u30a3\\u30fc\\u30b9\\u30d5\\u30a1\\u30c3\\u30b7\\u30e7\\u30f3,\\u30e1\\u30f3\\u30ba\\u30d5\\u30a1\\u30c3\\u30b7\\u30e7\\u30f3,\\u5b50\\u4f9b\\u670d,\\u30a2\\u30af\\u30bb\\u30b5\\u30ea\\u30fc,\\u9774.\",\n" +
            "      \"keywords_mx\": \"agatha ruiz de la prada, moda mujer, moda hombre, moda infantil, complementos, zapatos.\",\n" +
            "      \"keywords_cl\": \"agatha ruiz de la prada, moda mujer, moda hombre, moda infantil, complementos, zapatos.\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"221\",\n" +
            "      \"name\": \"Alcocer Anticuarios\",\n" +
            "      \"img\": \"http:\\/\\/madrid-shops.com\\/media\\/shops\\/alcocer-small.jpg\",\n" +
            "      \"logo_img\": \"http:\\/\\/madrid-shops.com\\/media\\/shops\\/logo-alcocer-200.jpg\",\n" +
            "      \"address\": \"Calle Santa Catalina, 5\",\n" +
            "      \"opening_hours_es\": \"lun-vi \\u00b7 10:00\\u201314:00, 17:00\\u201320:00\",\n" +
            "      \"opening_hours_en\": \"Monday to Friday: 10:00-14:00\\/17:00-20:00h\",\n" +
            "      \"opening_hours_cn\": \"\\u5468\\u4e00 - \\u5468\\u4e94\\uff1a10:00-14:00 \\/\\/ 17:00-20:00\",\n" +
            "      \"opening_hours_jp\": \" \\u6708\\u66dc\\uff5e\\u91d1\\u66dc10:00-14:00, 17:00-20:00 \",\n" +
            "      \"opening_hours_mx\": \"lun-vi \\u00b7 10:00\\u201314:00, 17:00\\u201320:00\",\n" +
            "      \"opening_hours_cl\": \"lun-vi \\u00b7 10:00\\u201314:00, 17:00\\u201320:00\",\n" +
            "      \"telephone\": \"(34) 91 429 79 19\",\n" +
            "      \"email\": \"\",\n" +
            "      \"url\": \"http:\\/\\/www.alcoceranticuarios.com\",\n" +
            "      \"description_jp\": \"Alcocer Anticuarios\\u306f1870\\u5e74\\u304b\\u3089\\u9aa8\\u8463\\u54c1\\u306e\\u58f2\\u8cb7\\u3092\\u884c\\u3046\\u53e4\\u7f8e\\u8853\\u5546\\u3067\\u3059\\u3002\\u5c02\\u9580\\u306b\\u6271\\u3063\\u3066\\u3044\\u308b\\u5546\\u54c1\\u306e\\u4e2d\\u3067\\u3082\\u5091\\u51fa\\u3057\\u3066\\u3044\\u308b\\u306e\\u306f\\u3001\\u30b9\\u30da\\u30a4\\u30f3\\u306e\\u30a2\\u30f3\\u30c6\\u30a3\\uff0d\\u30af\\u30b7\\u30eb\\u30d0\\uff0d\\u300118\\u4e16\\u7d00\\u306e\\u30a4\\u30ae\\u30ea\\u30b9\\u88fd\\u6642\\u8a08\\u300118-19\\u4e16\\u7d00\\u306e\\u30e8\\uff0d\\u30ed\\u30c3\\u30d1\\u306e\\u8abf\\u5ea6\\u54c1\\u3067\\u3059\\u3002\\u307e\\u305f\\u300119\\u4e16\\u7d00\\u306e\\u30b9\\u30da\\u30a4\\u30f3\\u88fd\\u9676\\u5668\\u3084\\u30b9\\u30da\\u30a4\\u30f3\\u7d75\\u753b\\u3082\\u5f7c\\u3089\\u306e\\u5049\\u5927\\u306a\\u30b3\\u30ec\\u30af\\u30b7\\u30e7\\u30f3\\u306b\\u5165\\u3063\\u3066\\u3044\\u307e\\u3059\\u3002\",\n" +
            "      \"description_mx\": \"Experimentados anticuarios en el sector, Alcocer Anticuarios se dedica a la compra y venta de antig\\u00fcedades DESDE el a\\u00f1o 1870.  Entre sus especialidades destacan la plata espa\\u00f1ola antigua, los relojes ingleses del siglo XVIII y el mobiliario europeo de los siglos XVIII y XIX. La cer\\u00e1mica y la pintura espa\\u00f1ola del siglo XIX se encuentran tambi\\u00e9n entre sus grandes aficiones.\",\n" +
            "      \"description_cl\": \"Experimentados anticuarios en el sector, Alcocer Anticuarios se dedica a la compra y venta de antig\\u00fcedades DESDE el a\\u00f1o 1870.  Entre sus especialidades destacan la plata espa\\u00f1ola antigua, los relojes ingleses del siglo XVIII y el mobiliario europeo de los siglos XVIII y XIX. La cer\\u00e1mica y la pintura espa\\u00f1ola del siglo XIX se encuentran tambi\\u00e9n entre sus grandes aficiones.\",\n" +
            "      \"description_cn\": \"\\u672c\\u5e97\\u4ece1870\\u5e74\\u5f00\\u59cb\\u4ece\\u4e8b\\u53e4\\u73a9\\u4e70\\u5356\\uff0c\\u85cf\\u54c1\\u5305\\u62ec\\u8bb8\\u591a\\u9ad8\\u6863\\u7684\\u53e4\\u8463\\u6587\\u7269\\uff0c\\u5176\\u4e2d\\u6700\\u4e3a\\u51fa\\u4f17\\u7684\\u8981\\u6570\\u897f\\u73ed\\u7259\\u53e4\\u94f6\\u5668\\u3001\\u5341\\u516b\\u5341\\u4e5d\\u4e16\\u7eaa\\u7684\\u82f1\\u56fd\\u949f\\u8868\\u548c\\u6b27\\u6d32\\u5bb6\\u5177\\u3002\\u9664\\u6b64\\u4e4b\\u5916\\uff0c\\u60a8\\u5728\\u8fd9\\u91cc\\u8fd8\\u80fd\\u8d2d\\u7f6e\\u5230\\u4e00\\u4e9b\\u53d7\\u6b22\\u8fce\\u7684\\u9676\\u74f7\\u4ee5\\u53ca\\u5341\\u4e5d\\u4e16\\u7eaa\\u897f\\u73ed\\u7259\\u7ed8\\u753b\\u4f5c\\u54c1\\u3002\",\n" +
            "      \"description_en\": \"Experienced antique dealers , Alcocer Antiques has been dedicated to buying and selling antiques since 1870 Specialities include  old Spanish silver, English clocks from the eighteenth century. European furniture of the eighteenth and nineteenth centuries. Spanish ceramics and painting from the nineteenth century are also among its exhibits. \",\n" +
            "      \"description_es\": \"Experimentados anticuarios en el sector, Alcocer Anticuarios se dedica a la compra y venta de antig\\u00fcedades DESDE el a\\u00f1o 1870.  Entre sus especialidades destacan la plata espa\\u00f1ola antigua, los relojes ingleses del siglo XVIII y el mobiliario europeo de los siglos XVIII y XIX. La cer\\u00e1mica y la pintura espa\\u00f1ola del siglo XIX se encuentran tambi\\u00e9n entre sus grandes aficiones.\",\n" +
            "      \"gps_lat\": \"40.4156535\",\n" +
            "      \"gps_lon\": \"-3.6976196000000527\",\n" +
            "      \"special_offer\": \"false\",\n" +
            "      \"categories\": [\n" +
            "        \"4\"\n" +
            "      ],\n" +
            "      \"keywords_es\": \"Alcocer Anticuarios, Galer\\u00edas de Arte\",\n" +
            "      \"keywords_en\": \"Alcocer Anticuarios, Art Galleries\",\n" +
            "      \"keywords_cn\": \"Alcocer Anticuarios \\u753b\\u5eca\\u3001\\u827a\\u672f\\u753b\\u5eca\",\n" +
            "      \"keywords_jp\": \"Alcocer Anticuarios, \\u30a2\\uff0d\\u30c8\\u30ae\\u30e3\\u30e9\\u30ea\\uff0d\",\n" +
            "      \"keywords_mx\": \"Alcocer Anticuarios, Galer\\u00edas de Arte\",\n" +
            "      \"keywords_cl\": \"Alcocer Anticuarios, Galer\\u00edas de Arte\"\n" +
            "    }\n" +
            "  ]}";

    public void testShopEntityMappedIntoShopsWorksWithNullData() {
        ShopEntityShopMapper mapper = new ShopEntityShopMapper();

        List<Shop> shops = mapper.map(null);

        assertNull(shops);
    }

    public void testShopEntityMappedIntoShopsWorksWithData() {
        ShopEntityShopMapper mapper = new ShopEntityShopMapper();

        List<Shop> shops = mapper.map(parseResponse(TEST_STRING));

        assertNotNull(shops);
        assertEquals(4, shops.size());
        assertEquals(310, shops.get(0).getId());
        assertEquals(221, shops.get(3).getId());
        assertEquals(40.4156535f, shops.get(3).getLatitude());

    }


    private List<ShopEntity> parseResponse(String response) {
        List<ShopEntity> result = null;
        try {
            Reader reader = new StringReader(response);
            Gson gson = new GsonBuilder().create();

            ShopResponse shopResponse = gson.fromJson(reader, ShopResponse.class);
            result = shopResponse.getResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
