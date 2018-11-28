import java.io.Closeable

fun using(obj: Closeable, block: ()->Unit){
   try{
      block()
   } finally{
      obj.close()
   }
}

class Request(val accept: String, val length: Int)
class Response(var statusCode: Int, var content: String){
   operator fun invoke(f : Response.()-> Unit){
      f()
   }
}

class Handler (val request : Request, var response: Response )

fun get ( path: String, f: Handler.() -> Unit ){

}

fun main(args: Array<String>){
   println("hello")

   get("/home") {
      if ( request.accept == "application/json"){
         response {
            content = ""
         }
      }

   }
}

