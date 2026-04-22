package divvvi.app.data.model

import com.google.firebase.Timestamp

data class Group(
    val id: String = "",
    val name: String? = null,
    val code: String = "",
    val createdAt: Timestamp? = null,
    val memberIds: List<String> = emptyList(),
)

data class Member(
    val id: String = "",
    val name: String = "",
    val colorHex: String = "#3D5AFE",
    val joinedAt: Timestamp? = null,
)

data class Entry(
    val id: String = "",
    val groupId: String = "",
    val createdAt: Timestamp? = null,
    val createdBy: String = "",
    val total: Double? = null,
)

data class Item(
    val id: String = "",
    val entryId: String = "",
    val name: String = "",
    val price: Double = 0.0,
    val confidence: Float = 1.0f,
    val assignedUserIds: List<String> = emptyList(),
)
