package com.smoothstack.utopia.jwtutils.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class JwtRequestModel implements Serializable {

   private static final long serialVersionUID = 2636936156391265891L;

   @Getter
   @Setter
   private String username;

   @Getter
   @Setter
   private String password;

}
