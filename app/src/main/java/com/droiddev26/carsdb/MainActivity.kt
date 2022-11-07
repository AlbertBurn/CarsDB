package com.droiddev26.carsdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import data.DataBaseHandler
import model.Car

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataBaseHandler = DataBaseHandler(this)
        Log.d("CarsCount:", dataBaseHandler.getCarsCount().toString())
        dataBaseHandler.addCar(Car("Toyota", "30 000 $"))
        dataBaseHandler.addCar(Car("Opel", "25 000 $"))
        dataBaseHandler.addCar(Car("Mercedes", "50 000 $"))
        dataBaseHandler.addCar(Car("Honda", "32 000 $"))
//        dataBaseHandler.addCar(Car("Mazda", "33 000 $"))
//        dataBaseHandler.addCar(Car("KIA", "22 000 $"))
//        dataBaseHandler.addCar(Car("LADA", "20 000 $"))
//        dataBaseHandler.addCar(Car("Skoda", "29 000 $"))
//
        val carList: ArrayList<Car> = dataBaseHandler.getAllCars()!!
        for (car in carList){
            Log.d("Car Info", "ID: " + car.id + ", name " + car.name + ", price " + car.price )
        }
    }
}