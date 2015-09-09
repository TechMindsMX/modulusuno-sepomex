package com.tim.modulusuno.sepomex.domain

import javax.persistence.*

@Entity
class User {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  Integer id


}
