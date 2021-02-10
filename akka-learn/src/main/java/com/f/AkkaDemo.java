package com.f;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class AkkaDemo extends UntypedAbstractActor {

    private LoggingAdapter log = Logging.getLogger(this.getContext().getSystem(), this);

    @Override
    public void preStart() throws Exception {
        super.preStart();
    }

//    @Override
//    public Receive createReceive() {
//
//        ActorRef sender = getSender();
//        log.info("send from: {}", sender.getClass());
//
//        self().tell("what", ActorRef.noSender());
//
//        return receiveBuilder()
//                .match(
//                        String.class,
//                        s -> log.info(s))
//                .matchAny(o -> {
//                    log.info("any" + o.toString());
//                })
//                .build();
//    }

    @Override
    public void onReceive(Object message) throws Throwable {
        log.info("{}", message);
    }

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("sys");
        ActorRef actorRef = system.actorOf(Props.create(AkkaDemo.class), "actorDemo");
        actorRef.tell("hello world", ActorRef.noSender()); // ActorRef.noSender()实际上就是叫做deadLetters的actor


        actorRef.tell("test", ActorRef.noSender());

        int uid = actorRef.path().uid();
        actorRef.tell(uid, ActorRef.noSender());

    }
}
