package com.tim.modulusuno.sepomex.domain

import javax.persistence.*

@Entity
class Sepomex {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  Integer id

  @Column(name="d_codigo")
  private String dCodigo

  @Column(name="d_asenta")
  private String dAsenta

  @Column(name="d_tipo_asenta")
  private String dTipoAsenta

  @Column(name="d_mnpio")
  private String dMnpio

  @Column(name="d_estado")
  private String dEstado

  @Column(name="d_ciudad")
  private String dCiudad

  @Column(name="d_CP")
  private String dCP

  @Column(name="c_estado")
  private String cEstado

  @Column(name="c_oficina")
  private String cOficina

  @Column(name="c_CP")
  private String cCP

  @Column(name="c_tipo_asenta")
  private String cTipoAsenta

  @Column(name="c_mnpio")
  private String cMnpio

  @Column(name="id_asenta_cpcons")
  private String idAsentaCpcnos

  @Column(name="d_zona")
  private String dZona

  @Column(name="c_cve_ciudad")
  private String cCveCiudad

}
