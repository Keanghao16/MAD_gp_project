package kh.edu.rupp.ite.madproject.model

data class ApiState<T>(
    val state: State,
    val data: T?
)

enum class State {
    loading, success, error
}