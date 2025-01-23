package kh.edu.rupp.ite.madproject.model;

public class ApiState <T> {
    val state: State;
    val data: T?;
    val message: String?;
}

enum class State{
    loading, success, error
}