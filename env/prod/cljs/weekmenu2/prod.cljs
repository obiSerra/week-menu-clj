(ns weekmenu2.prod
  (:require [weekmenu2.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
