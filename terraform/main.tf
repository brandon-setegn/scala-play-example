module "service_account" {
  source     = "terraform-google-modules/service-accounts/google"
  version    = "~> 4.2"
  project_id = var.project_id
  prefix     = "sa-cloud-run"
  names      = ["simple"]
  project_roles = ["${var.project_id}=>roles/secretmanager.secretAccessor"]
}

module "cloud_run" {
  source  = "GoogleCloudPlatform/cloud-run/google"
  version = "~> 0.10"

  service_name          = "cloud-run-example-scala"
  project_id            = var.project_id
  location              = "us-east1"
  image                 = "us-east1-docker.pkg.dev/${var.project_id}/cloud-run-example/scala-play-example:latest"
  service_account_email = module.service_account.email
  env_secret_vars       = [
    {
      name = "APPLICATION_SECRET"
      value_from = [
        {
          secret_key_ref = {
            name = "cloud-run-example-play-secret"
            key  = "latest"
          }
        }
      ]
    },
    {
      name = "ALLOWED_HOST"
      value_from = [
        {
          secret_key_ref = {
            name = "cloud-run-example-play-host"
            key  = "latest"
          }
        }
      ]
    }]
}

resource "google_cloud_run_service_iam_binding" "binding" {
  project  = module.cloud_run.project_id
  location = module.cloud_run.location
  service  = module.cloud_run.service_name
  role     = "roles/run.invoker"
  members = [
    "allUsers"
  ]
}
