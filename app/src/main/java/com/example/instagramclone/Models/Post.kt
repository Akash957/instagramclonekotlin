package com.example.instagramclone.Models

class Post {
    var postUri: String = ""
    var caption: String = ""
    var name:String=""
    var time:String=""

    constructor()
    constructor(postUri: String, caption: String) {
        this.postUri = postUri
        this.caption = caption
    }

    constructor(postUri: String, caption: String, name: String, time: String) {
        this.postUri = postUri
        this.caption = caption
        this.name = name
        this.time = time
    }

}