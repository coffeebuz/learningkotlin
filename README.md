
# A learning repo for kotlin

This is a personal record of learning kotlin. 

## Creating a DSL
https://www.youtube.com/watch?v=3K3oQ22nqfo&t=2702s


```
class Request(val accept: String, val length: Int)
class Response(var statusCode: Int, var content: String)

fun get ( path: String, f: (Request, Response) -> Unit ){

}

fun main(args: Array<String>){
   println("hello")

   get("/home") {
      request, response -> if ( request.accept == "application/json"){
        response.content = ""
      }

   }
}

```

 `request, response -> ` is noise , to remove it we can change `f` to take a single parameter and then we can use it

```
class Handler (val request : Request, var response: Response )

fun get ( path: String, f: (Handler) -> Unit ){

}

fun main(args: Array<String>){
   println("hello")

   get("/home") {
     if ( it.request.accept == "application/json"){
        it.response.content = ""
     }

   }
}
```

`it.` looks like noise. In kotlin we have extension methods like this

```
    fun Handler.extensionMethodName {
        // body
    }
```

It is also possible to define lambda extension method, the type for something like this is

```
    Handler.()->Unit
```

So this means that the lambda definition will have access to the receiver object `this` of type `Handler`. It also means that we have access to the properties without qualifying with it and this.

```
    fun get ( path: String, f: Handler.() -> Unit ){

    }

    fun main(args: Array<String>){
       println("hello")

       get("/home") {
          if ( request.accept == "application/json"){
            response.content = ""
          }

       }
    }
```


Just like python `__call__`, kotlin has operator `invoke`, so we could potentially we can do this `response(params)` or more targeted to our intent

```
    response {
        content = ""
    }
```

when we include the invoke operator that takes in lambda extension on Response

```
class Response(var statusCode: Int, var content: String){
   operator fun invoke(f : Response.()-> Unit){
      f()
   }
}
```


