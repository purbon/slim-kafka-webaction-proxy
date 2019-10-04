# An slim Kafka Proxy to track web actions

Interested to track user behaviour directly from your website into Apache Kafka, the slim Kafka web action proxy is a solution for this.
By implementing a very basic api, including options to filter unwanted data points, this proxy will
allow your web properties to be directly send into Apache Kafka.

## API
POST /actions/:actionLabel/users/:userId (Async)