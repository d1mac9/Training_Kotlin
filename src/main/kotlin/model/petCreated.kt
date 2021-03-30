package model

data class petCreated(
    val category: Category,
    val id: Long,
    val name: String,
    val photoUrls: List<String>,
    val status: String,
    val tags: List<Tag>
)

data class Category(
    val id: Int,
    val name: String
)

data class Tag(
    val id: Int,
    val name: String
)