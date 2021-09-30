package com.example.jetpack_practice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

//Solving log running and safe to call from main thread problem using coroutine
/*
* Long running tasks  block the main
* Suspend functions
* Coroutines will run on the main thread, and suspend does not mean background.
* When all of the coroutines on the main thread are suspended, the main thread is free to do other work.
*
* Well written suspend functions are always safe to call from the main thread
* For Too long ruing operations, telling which thread to use, increases performance also safe to call
* For that, uses withContext function and assign dispatcher
* Dispatchers.Main - Main thread on Android, interact with the UI and perform light work
* Dispatchers.IO - Optimized for disk and network IO off the main thread
* Dispatchers.Default - Optimized for CPU intensive work off the main thread
*
*  */

// Dispatchers.Main
suspend fun fetchDocs() {
    // Dispatchers.Main
    val result = get("developer.android.com")
    // Dispatchers.Main
    show(result)
}
// Dispatchers.Main
suspend fun get(url: String) =
    // Dispatchers.Main
    withContext(Dispatchers.IO) {
        // Dispatchers.IO
        /* perform blocking network IO here */
    }
// Dispatchers.Main
//--------------------------------------------------------------------------

//Keeping track of coroutines

/*
* A leaked coroutine can waste CPU, disk, or even launch a network request that’s not needed.
* structured concurrency can solve it by doing 3 tings
* Cancel work with scopes-A CoroutineScope keeps track of all your coroutines, and it can cancel all of the coroutines started in it.
* To cancel them , we need to start coroutine with CoroutineScope,
* start coroutines- Launch, async, runBlocking and its scopes
* Creating in the ViewModel,so keep tack of all coroutine, and its benefit over creating them in activity/fragment
* And coroutine canceled, when ViewModel get canceled
* coroutineScope and supervisorScope let you safely launch coroutines from suspend functions.
* */
class MyViewModel(): ViewModel() {
    fun userNeedsDocs() {
        // Start a new coroutine in a ViewModel
        viewModelScope.launch {
            fetchDocs()
        }
    }
}

suspend fun fetchTwoDocs() {
    coroutineScope {
        launch { fetchDoc(1) }
        async { fetchDoc(2) }
    }
}
//--------------------------------------------------------------------------

//Coroutine handling
/*
* Job-handler for lunch
* Deferred - handler for async*/

//--------------------------------------------------------------------------

//Exception Handling in Kotlin Coroutines

/*
* launch user try/catch or by providing CoroutineExceptionHandler.
* In the case of async, it’s not the case. It holds the exception until await is invoked. So we can wrap the .await() call inside try/catch
*
*/

scope.launch {
    try {
        doSomething()
    } catch(e: Exception) {
        // Handle exception logic
    }
}

viewModelScope {
    val deferredResult = async {
        doSomething()
    }
    try {
        deferredResult.await()
    } catch(e: Exception) {
        // Handle exception thrown
    }
}