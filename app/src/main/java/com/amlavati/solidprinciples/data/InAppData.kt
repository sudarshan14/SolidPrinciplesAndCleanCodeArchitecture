package com.amlavati.solidprinciples.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

fun getTopicsListWIthDescription(): List<TopicWithDescription> {

    val list = mutableListOf<TopicWithDescription>()
    list.add(
        TopicWithDescription(
            "S",
            "Single Responsibility Principle",
            "This states that a class should have one\\n\" +\n" +
                    "            \"responsibility or one reason to change."
        )
    )
    list.add(
        TopicWithDescription(
            "O",
            "Open Closed Principle",
            "This states that a class should be open for extension and\n" +
                    "closed for modification"
        )
    )
    list.add(
        TopicWithDescription(
            "L",
            "Liskov Substitution Principle",
            "This states that a parent class should be replaced\n" +
                    "by a child class without changing the behavior of the system. An example of this\n" +
                    "principle is if you have a class called Bird and a sub-class called Duck. If you are\n" +
                    "using references of Bird in your code and substitute those usages with Duck,\n" +
                    "then your code should remain unchanged. A famous example of a violation of\n" +
                    "this principle is having a Rectangle class with two members named width and\n" +
                    "height and a sub-class named Square. In reality, a square is a rectangle, but our\n" +
                    "modeling of a square wouldn't be a rectangle because the rules in Square would\n" +
                    "mean that the width and height will always have to be the same. If you were to swap\n" +
                    "these two dependencies, then your code would break."
        )
    )
    list.add(
        TopicWithDescription(
            "I",
            "Interface Segregation Principle",
            "This states that we should avoid using large\n" +
                    "interfaces and instead break them up into smaller interfaces. The idea here is that\n" +
                    "code shouldn't depend on methods it doesn't use. An example of this is defining\n" +
                    "interfaces whose methods don't need to be implemented. A good example\n" +
                    "of this is the approach that's taken in Android user interfaces by separating\n" +
                    "OnClickListener, OnLongClickListener, and OnTouchListener."
        )
    )
    list.add(
        TopicWithDescription(
            "D", "Dependency Inversion Principle", "This states that we should depend on\n" +
                    "abstractions rather than concretions. The idea here is to depend as much as possible\n" +
                    "on abstract classes and interfaces. This can be very difficult to achieve considering\n" +
                    "that we rely on concretions a lot of the time. Here, we should identify parts of the\n" +
                    "code that are constantly developed and subject to change and introduce layers of\n" +
                    "abstractions between our code and these classes. A good way to protect against\n" +
                    "this is through dependency injection frameworks such as Dagger and Hilt, which\n" +
                    "generate factories to create volatile components"
        )
    )

    return list
}

data class TopicWithDescription(
    val topic: String,
    val fullForm: String,
    val description: String
)