package com.tim.modulusuno.sepomex

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@RestController
@RequestMapping("/sepomex")
class SepomexController {

  @RequestMapping(value="/show", method=RequestMethod.GET)
  String show() {
    "Greetings from Spring Boot!"
  }

}
