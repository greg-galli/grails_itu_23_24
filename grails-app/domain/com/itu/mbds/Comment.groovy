package com.itu.mbds

class Comment {

    String content
    Date dateCreated
    Date lastUpdated
    Integer likeCount = 0
    User author

    static belongsTo = [post: Post]

    static constraints = {
        content blank: false, nullable: false
        likeCount nullable: false, min: 0
        author nullable: false
        post nullable: false
    }

    static mapping = {
        content type: 'text'
    }
}
