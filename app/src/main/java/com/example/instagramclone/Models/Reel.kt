package com.example.instagramclone.Models

class Reel {
    var reelUri: String = ""
    var caption: String = ""
    var profileLink: String? = null

    constructor()
    constructor(reelUri: String, caption: String) {
        this.reelUri = reelUri
        this.caption = caption
    }

    constructor(reelUri: String, caption: String, profileLink: String) {
        this.reelUri = reelUri
        this.caption = caption
        this.profileLink = profileLink
    }
}