package com.itu.mbds

class Post {

    String title
    String content = ""
    Date dateCreated
    Date lastUpdated
    Integer likeCount = 0
    File file

    static belongsTo = [author: User]

    static hasMany = [tags: Tag, comments: Comment]

    static constraints = {
        title nullable: false, blank: false
        content nullable: false, blank: true
        likeCount nullable: false, min: 0
        file nullable: false
        author nullable: false
        tags nullable: true
        comments nullable: true
    }
}
