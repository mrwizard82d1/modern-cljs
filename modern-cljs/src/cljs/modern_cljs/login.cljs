(ns modern-cljs.login
  (:require [domina.core :refer [by-id value set-value!]]))

;; define the function to be attached to the form submission event
(defn validate-form []
  ;; get email and password elements from the document
  (if (and (> (count (value (by-id "email"))) 0)
           (> (count (value (by-id "password"))) 0))
    true
    (do (js/alert "Please, complete the form!")
        false)))

;; define the function to attach validate-form to the onsubmit event of the form
(defn init []
  ;; verify that js/document exists and that it has a getElementById property
  (if (and js/document
           (.-getElementById js/document))
    ;; get the loginForm by element id and set its onsubmit property to our validate-form function
    (let [login-form-element (.getElementById js/document "loginForm")]
      (set! (.-onsubmit login-form-element) validate-form))))

(set! (.-onload js/window) init)
