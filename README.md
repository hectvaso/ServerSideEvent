# ServerSideEvent

Del lado del servidor en spring 
=====================================================================================================================

Es mejor usar un  ResponseBodyEmitter junto con su propio subproceso asíncrono dedicado y envuelto con un ResponseEntity  (en el que podemos inyectar el emisor directamente):

@Controller
public class ResponseBodyEmitterController {
 
    private ExecutorService executor 
      = Executors.newCachedThreadPool();

    @GetMapping("/rbe")
    public ResponseEntity<ResponseBodyEmitter> handleRbe() {
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        executor.execute(() -> {
            try {
                emitter.send(
                  "/rbe" + " @ " + new Date(), MediaType.TEXT_PLAIN);
                emitter.complete();
            } catch (Exception ex) {
                emitter.completeWithError(ex);
            }
        });
        return new ResponseEntity(emitter, HttpStatus.OK);
    }
}
Entonces, en el ejemplo anterior,  podemos eludir la necesidad de usar CompleteableFutures , promesas asincrónicas más complicadas o el uso de la anotación @Async .

En su lugar, simplemente declaramos nuestra entidad asincrónica y la envolvemos en un nuevo subproceso proporcionado por ExecutorService.

Del lado del cliente con spring 
======================================================================================================================

Del lado del cliente con JavaScript