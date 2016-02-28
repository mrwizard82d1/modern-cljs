(set-env!
 :source-paths #{"src/cljs"}
 :resource-paths #{"html"}

 :dependencies '[[org.clojure/clojure "1.7.0"] ; explicitly specify Clojure and ClojureScript as recommended
                 [org.clojure/clojurescript "1.7.228"] ; by the boot-cljs-repl author
                 [adzerk/boot-cljs "1.7.228-1"] ; compile ClojureScript
                 [pandeiro/boot-http "0.7.3"] ; serve resoures via http
                 [adzerk/boot-reload "0.4.5"] ; reload static resources
                 [adzerk/boot-cljs-repl "0.3.0"] ; browser REPL
                 [com.cemerick/piggieback "0.2.1"] ; must explicitly include dependencies for boot-cljs-repl
                 [weasel "0.7.0"]
                 [org.clojure/tools.nrepl "0.2.12"]
                 ]) 

(require '[adzerk.boot-cljs :refer [cljs]] ; make cljs task visible to boot
         '[pandeiro.boot-http :refer [serve]] ; serve task visible to boot
         '[adzerk.boot-reload :refer [reload]] ; reload task visible to boot
         '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]) ; REPL task visible to book

;; Define a dev task to provide the immediate feedback environment. 
;; This task relieves us of the need to remember the details about the task pipeline
;; that we must supply at the command line.
(deftask dev
  "Launch the Immediate Feedback Development Environment."
  []
  ;; Remember to specify the arguments to `comp` in **reverse** order of execution (that is, the first argument to
  ;; `comp` is the last task actually run).
  (comp
   (serve :dir "target")
   (watch)
   (reload)
   (cljs-repl) ; This task must occur before the cljs task (although it comes **after** it in the pipeline).
   (cljs)
   (target :dir #{"target"})))
