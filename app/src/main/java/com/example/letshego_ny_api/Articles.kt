package com.example.letshego_ny_api

/*Author Kgethego Labobedi
* kgethegolabobedi@gmail.com
* 20-Jul-2021
* */
//This is a model class with constructors and getters.
class Articles {
    var title: String? = null
    var asset_id: String? = null
    var source: String? = null
    var publish_date: String? = null
    var updated: String? = null
    var section: String? = null
    var subsection: String? = null
    var byline: String? = null
    var type: String? = null

    constructor(
        title: String?,
        asset_id: String?,
        source: String?,
        publish_date: String?,
        updated: String?,
        section: String?,
        subsection: String?,
        byline: String?,
        type: String?
    ) {
        this.title = title
        this.asset_id = asset_id
        this.source = source
        this.publish_date = publish_date
        this.updated = updated
        this.section = section
        this.subsection = subsection
        this.byline = byline
        this.type = type
    }

    constructor() {}
}