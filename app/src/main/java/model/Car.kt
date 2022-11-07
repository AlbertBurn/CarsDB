package model

class Car {
    var id:Int = 0
        get() {
            return field
        }
        set(value) {
            field = value
        }
    var name: String = ""
        get() {
            return field
        }
        set(value) {
            field = value
        }
    var price: String = ""
        get() {
            return field
        }
        set(value) {
            field = value
        }


    constructor()

    constructor(id: Int, name: String, price: String) {
        this.id = id
        this.name = name
        this.price = price
    }

    constructor(name: String, price: String) {
        this.name = name
        this.price = price
    }


}