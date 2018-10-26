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

package cn.scb.sample.providerV1.controller;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.scb.sample.providerV1.dao.AccountInfo;
import cn.scb.sample.providerV1.dao.AccountMapper;

@RestSchema(schemaId = "accountEndpoint")
@RequestMapping(path = "/v2")
public class AccountEndpoint {
  private AccountMapper accountMapper;

  @Autowired
  public AccountEndpoint(AccountMapper accountMapper) {
    this.accountMapper = accountMapper;
  }

//  @PostMapping(path = "/create")
//  public void createTable(long id, double balance) {
//    AccountInfo info = new AccountInfo();
//    info.setUserId(id);
//    info.setReserved(false);
//    info.setTotalBalance(balance);
//    accountMapper.createAccountInfo(info);
//  }

  @GetMapping(path = "/query")
  public AccountInfo query(@RequestParam long id) {
    return accountMapper.getAccountInfo(id);
  }

  @PostMapping(path = "/update")
  public void pay(long id, double balance) {
    AccountInfo info = this.query(id);
    if (info != null) {
      info.setTotalBalance(info.getTotalBalance() + balance);
      accountMapper.updateAccountInfo(info);
    }
  }

//  @DeleteMapping(path = "/clear")
//  public void clearTable() {
//    accountMapper.clear();
//  }
}
