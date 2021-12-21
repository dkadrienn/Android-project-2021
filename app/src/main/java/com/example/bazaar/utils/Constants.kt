package com.example.bazaar.utils

object Constants {
    const val BASE_URL = "https://pure-gorge-51703.herokuapp.com/"
    const val REGISTER_URL = "user/register"
    const val LOGIN_URL = "user/login"
    const val PASSWORD_RESET_URL = "user/reset"
    const val GET_PRODUCT_URL = "products"
    const val Add_PRODUCT = "products/add"
    const val GET_USER_DATA = "user/data"
    const val GET_ORDERS_URL = "orders"
    const val ADD_ORDER_URL = "orders/add"
    const val REMOVE_PRODUCT_URL = "products/remove?product_id={product_id}"
    const val USER_UPDATE_URL = "user/update"
    const val PASSWORD_RESET_TOKEN_URL = "user/reset"

    const val SHARED_PREF_FILE = "MySharedPref"
    const val sharedPrefKeyToken = "token"
    const val sharedPrefKeyUser = "user"
    const val sharedPrefKeyUsername = "username"
    const val sharedPrefKeyEmail = "email"
    const val sharedPrefKeyPhoneNr = "phoneNr"
}