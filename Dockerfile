FROM clojure:openjdk-14-tools-deps

WORKDIR /opt/rascal

COPY ./deps.edn /opt/rascal
RUN clj -Stree

COPY . /opt/rascal

CMD clojure -X dokku-clojure.repl-issue/run