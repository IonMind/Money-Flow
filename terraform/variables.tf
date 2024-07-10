variable "provide_config" {
    type = map(string)
    description = "provider configuration variable"
    default = {
        project = "confident-inn-428917-k6"
        region = "us-central1"
        zone = "us-central1-a"
        authfile = "../gcp-key.json"
    }
}