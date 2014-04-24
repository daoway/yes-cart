/*
 * Copyright 2009 Igor Azarnyi, Denys Pavlov
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.yes.cart.shoppingcart.impl;

import org.junit.Test;
import org.yes.cart.BaseCoreDBTestCase;
import org.yes.cart.domain.entity.Customer;
import org.yes.cart.shoppingcart.AmountCalculationStrategy;
import org.yes.cart.shoppingcart.ShoppingCart;
import org.yes.cart.shoppingcart.ShoppingCartCommand;
import org.yes.cart.shoppingcart.ShoppingCartCommandFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * User: Igor Azarny iazarny@yahoo.com
 * Date: 09-May-2011
 * Time: 14:12:54
 */
public class LogoutCommandImplTest extends BaseCoreDBTestCase {

    @Test
    public void testExecute() {

        final Customer customer = createCustomer();

        ShoppingCart shoppingCart = new ShoppingCartImpl();
        shoppingCart.initialise(ctx().getBean("amountCalculationStrategy", AmountCalculationStrategy.class));
        final ShoppingCartCommandFactory commands = ctx().getBean("shoppingCartCommandFactory", ShoppingCartCommandFactory.class);

        Map<String, String> params = new HashMap<String, String>();
        params.put(ShoppingCartCommand.CMD_LOGIN_P_EMAIL, customer.getEmail());
        params.put(ShoppingCartCommand.CMD_LOGIN_P_NAME, "John Doe");
        params.put(LoginCommandImpl.CMD_LOGIN, "1");
        commands.execute(shoppingCart, (Map) params);
        assertEquals(ShoppingCart.LOGGED_IN, shoppingCart.getLogonState());

        assertNotNull(shoppingCart.getShoppingContext().getCustomerEmail());
        assertEquals("Test that auth saved to cart",
                customer.getEmail(),
                shoppingCart.getShoppingContext().getCustomerEmail());

        commands.execute(shoppingCart,
                (Map) Collections.singletonMap(ShoppingCartCommand.CMD_LOGOUT, ShoppingCartCommand.CMD_LOGOUT));

        assertNull(shoppingCart.getCustomerEmail());
        assertNull(shoppingCart.getCustomerName());
        assertEquals(ShoppingCart.NOT_LOGGED, shoppingCart.getLogonState());
    }
}