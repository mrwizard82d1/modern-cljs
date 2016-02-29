;; create the main project namespace
(ns modern-cljs.core
  (:require [weasel.repl :as repl]))

(repl/connect "ws://localhost:9001")

;; enable ClojureScript to print to the JavaScript console of the browser
(enable-console-print!)

;; print to the console
(println "Hello, Modern ClojureScript World!")
