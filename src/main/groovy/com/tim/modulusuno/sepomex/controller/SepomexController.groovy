package com.tim.modulusuno.sepomex

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.beans.factory.annotation.Autowired

@RestController
@RequestMapping("/sepomex")
class SepomexController {

  @Autowired
  SepomexRepository repository

  @RequestMapping(value="/show", method=RequestMethod.GET)
  String show(@RequestParam("id") Integer id) {

    def sepomexes = repository.findAllByDCodigo(id)

    def jsonData = [
      id:sepomexes[0]?.id,
      dCodigo:sepomexes[0]?.dCodigo,
      dTipoAsenta:sepomexes[0]?.dTipoAsenta,
      dMnpio:sepomexes[0]?.dMnpio,
      dEstado:sepomexes[0]?.dEstado,
      dCiudad:sepomexes[0]?.dCiudad,
      dCP:sepomexes[0]?.dCP,
      cEstado:sepomexes[0]?.cEstado,
      cOficina:sepomexes[0]?.cOficina,
      cCP:sepomexes[0]?.cCP,
      cTipoAsenta:sepomexes[0]?.cTipoAsenta,
      cMnipio:sepomexes[0]?.cMnipio,
      idAsentaCpcnos:sepomexes[0]?.idAsentaCpcnos,
      dZona:sepomexes[0]?.dZona,
      cCveCiudad:sepomexes[0]?.cCveCiudad,
      dAsenta: sepomexes*.dAsenta
    ]

    jsonData
  }

}
