package com.tim.modulusuno.sepomex

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping

@RestController
class SepomexController {

  @RequestMapping("/")
  String index() {
    "Greetings from Spring Boot!"
  }

}
