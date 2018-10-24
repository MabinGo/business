/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.scb.sample.providerA.controller;

import javax.ws.rs.core.Response.Status;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.apache.servicecomb.swagger.invocation.context.ContextUtils;
import org.apache.servicecomb.swagger.invocation.context.InvocationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestSchema(schemaId = "seattrackEndpoint")
@RequestMapping(path = "/providerA")
public class SeattrackEndpoint {

  //  @RequestMapping(path = "/seat_tracks",method = RequestMethod.POST)
  @PostMapping(path = "/seat_tracks")
  public String createSeatTracks() {

    //customize response status
    InvocationContext context = ContextUtils.getInvocationContext();
    context.setStatus(Status.CREATED);

    return "seat_tracks";
  }

  @PutMapping(path = "/seat_track")
  public int updateSeatTracks(@RequestParam int id) {

    //customize response status
    InvocationContext context = ContextUtils.getInvocationContext();
    context.setStatus(Status.ACCEPTED);

    return id;
  }

  @GetMapping(path = "/seat_track")
  public int getSeatTracks(@RequestParam int id) {
    return id;
  }

  @GetMapping(path = "/incompleted_seattrack")
  public String getIncompletedSeattracks() {
    return "getIncompletedSeattracks";
  }
}