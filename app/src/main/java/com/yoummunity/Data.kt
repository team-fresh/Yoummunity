package com.yoummunity

import org.json.JSONArray
import org.json.JSONObject

data class Data(
    val kind: String,
    val etag: String,
    val nextPageToken: String,
    val pageInfo: PageInfo,
    val items: List<Item>
)

data class PageInfo(
    val totalResults: Int,
    val resultsPerPage: Int
)

data class Item(
    val kind: String,
    val etag: String,
    val id: String,
    val snippet: BigSnippet
)

data class BigSnippet(
    val videoId: String,
    val topLevelComment: TopLevelComment
)

data class TopLevelComment(
    val kind: String,
    val etag: String,
    val id: String,
    val snippet: SmallSnippet,
    val canReply: Boolean,
    val totalReplyCount: Int,
    val isPublic: Boolean
)

data class SmallSnippet(
    val videoId: String,
    val textDisplay: String,
    val textOriginal: String,       // comment
    val authorDisplayName: String,  // author
    val authorProfileImageUrl: String,
    val authorChannelUrl: String,
    val authorChannelId: AuthorChannelId,
    val canRate: Boolean,
    val viewerRate: String,
    val likeCount: Int,
    val publishedAt: String,        // ISO 8601 [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss]Z
    val updatedAt: String           // ISO 8601 [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss]Z
)

data class AuthorChannelId(
    val value: String
)