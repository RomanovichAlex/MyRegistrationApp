package by.romanovich.myregistrationapp.ui.state


enum class ViewState(val value: Int) {
    INIT(0), IS_SUCCESS(1), ERROR(2);

    companion object {
        fun fromInt(value: Int) = values().first { it.value == value }
    }
}