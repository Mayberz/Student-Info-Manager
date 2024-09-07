
package com.yo.swaggerConfig;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(info = @Info( 
		  title =  "System Info Manager",
		  contact = @Contact( name = "umbrella corporation",
		  email = "umbrellacorpo@gmail.com"),
		  license = @License(name =  "BT-7274"),
          version = "V1"),
          security = @SecurityRequirement(name = "Authentication"))
@SecurityScheme(name = "Authentication",in = SecuritySchemeIn.HEADER,
                 type = SecuritySchemeType.HTTP,
                  bearerFormat = "JWT",scheme = "bearer")
public class SwaggerDocConfig {
	


}
