package com.itu.mbds

class Tag {

    String name

    static constraints = {
        name nullable: false, blank: false, unique: true
    }
}
