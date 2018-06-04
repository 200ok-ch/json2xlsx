(ns json2xlsx.core
  (:gen-class)
  (:require [dk.ative.docjure.spreadsheet :refer [create-workbook save-workbook!]]
            [cheshire.core :refer [parse-string]]
            [clojure.java.io :refer [delete-file]]))

;; https://stackoverflow.com/questions/23018870/how-to-read-a-whole-binary-file-nippy-into-byte-array-in-clojure

(defn- utf8-string [s]
  (String. s "UTF-8"))

(defn- tmpdir []
  (or (System/getenv "TMPDIR") "/tmp"))

;; TODO maybe use (.deleteOnExit my-temp-file)
(defn- tmpfile []
  (java.io.File/createTempFile "spreadsheet_" ".xslx"));; (tmpdir)))

(defn -main
  [& args]
  ;; TODO make tmp file
  (let [file (tmpfile)]
    ;;(println path)
    (->> *in*
         slurp
         ;;print
         ;;;;utf8-string
         parse-string ;; maybe use parse-stream
         ;;;; check against spec
         (create-workbook "plan")
         (save-workbook! "x.xslx")
         )
    ;;(print (.getAbsolutePath file))
    (print (slurp (java.io.File. "x.xslx")))
    ;;(delete-file path)

    ))
