// tag::copyright[]
/*******************************************************************************
 * Copyright (c) 2017, 2018 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - Initial implementation
 *******************************************************************************/
// end::copyright[]
package io.openliberty.guides.common;

// JSON-P
import javax.json.Json;
import javax.json.JsonObject;
import io.openliberty.guides.config.Email;

public enum JsonMessages {

  SERVICE_UNREACHABLE();

  private JsonObject json;

  public JsonObject getJson() {
    switch (this) {
    case SERVICE_UNREACHABLE:
      this.serviceUnreachable();
      break;
    default:
      break;
    }
    return json;
  }

  private void serviceUnreachable() {
    json = Json.createObjectBuilder()
               .add("ERROR",
                    "Unknown hostname or the resource may not be running on the host machine")
               .build();
  }

  public static JsonObject serviceInMaintenance(String service) {
    return Json.createObjectBuilder().add(service, "Service is temporarily down for maintenance")
               .build();
  }

  // tag::returnMessage[]
  public static JsonObject returnMessage(String service, Email devEmail) {

    JsonObject contact = Json.createObjectBuilder().add("Email", devEmail.toString())
                             .add("Name", devEmail.getEmailName())
                             .add("Domain", devEmail.getEmailDomain()).build();
    return Json.createObjectBuilder().add("Status", serviceInMaintenance(service))
               .add("Contact", contact).build();
  }
  // end::returnMessage[]

}
