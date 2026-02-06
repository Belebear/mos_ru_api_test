package specs;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static helpers.CustomAllureListener.withCustom;

public class RequestSpec {

    public static RequestSpecification baseSpec(){
        return new RequestSpecBuilder()
                .addFilter(withCustom())
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }
}
