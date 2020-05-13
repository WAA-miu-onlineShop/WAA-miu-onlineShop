let totalAmount = 0.0;
$("#addToCartForm").submit(function (event) {
    let selectedProduct = $("#cartProduct").val();
    let selectedProductArr = selectedProduct.split(":@");
    let selectedQty = $("#cartItemQty").val();

    let amount = parseInt(selectedQty) * parseFloat(selectedProductArr[5]);


    let cartEntry = `<tr><td scope='row'>${selectedProductArr[1]}</td>
                <td>${selectedProductArr[2]}</td>
                <td>${selectedQty}</td>
                <td>${selectedProductArr[5]}</td>
                <td>${amount}</td>
                </tr>`;
    $("#shoppingCartBody").append(cartEntry);
    $("#totalCartAmount").html(computeTotalCartAmount(amount));
    $("#shoppingCart").css("display","block");
    $(".otherContent").css("display","none");
    event.preventDefault();
});

function computeTotalCartAmount(rowAmount){
    totalAmount += rowAmount;
    return totalAmount;
}

function loadAddressForm(){
    const targetDiv = $("#loadAddressForm");
    const selectedRole = $("#roles");
    if(selectedRole.trim().toLowerCase() == "buyer"){
        targetDiv.css("display","block");

    }
}

function checkSellerStatus(){
    let username = $("#usernameDisplay").html();
    console.log(username);
    $.ajax({
        "url": "http://localhost:8080/seller/sellerStatus/" + username,
        "type": "GET",
        "dataType": "json",
        "contentType": "application/json",
    }).done(function (data) {
        console.log(data);

    }).fail(function (xhr, status, exception) {
        console.log(xhr.status, xhr, status, exception);
    });
}


$("#addProductNavBtn").click(function () {
    checkSellerStatus();
});

$("#createMenuNavBtn").click(function () {
    let html1 = "";
    let newMenuContent = $("#newMenuContent");
    let modalHeader = $("#menuModalLabel");

    modalHeader.html("Input Address Information");

    $.ajax({
        "url": "http://localhost:8083/pick_n_go_app_war_exploded/items",
        "type": "GET"
    }).done(function (itemsObj) {
        itemsObj.data.forEach(function (obj) {
            html1 += `<div class='form-check'><label class='form-check-label'>
            <input type='radio' class='form-check-input' value='${obj.itemId}' name='${obj.itemId}'>${obj.itemName}</label></div>`;

        });
        html1 += `<div class="form-group"><label for="category">Category</label><select id="categoryMenu" class="form-control" required><option value="">-Select Category-</option><option value="breakfast">Breakfast</option><option value="lunch">Lunch</option><option value="dinner">Dinner</option></select></div>`;
        newMenuContent.html(html1);
    }).fail(function (xhr, status, exception) {
        console.log(xhr.status, xhr, status, exception);
        //return {};
    });



});


$("#menuBreakfast").click(function (event) {

    $.ajax({
        "url": "http://localhost:8083/pick_n_go_app_war_exploded/menus",
        "type": "GET"
    }).done(function (menuObj) {
        let html = "<table class='table table-bordered'>" +
            "<thead>" +
            "<tr><th scope='col'>#</th><th scope='col'>Item ID</th><th scope='col'>Item Name</th><th scope='col'>Description</th></tr>" +
            "</thead><tbody>";


        let i = 0;

        menuObj.data.forEach(function (obj) {
            if (obj.category === "breakfast") {
                obj.items.forEach(function (item) {
                    html += `<tr><th scope='row'>${++i}</th><td>${item.itemId}</td><td>${item.itemName}</td><td>${item.description}</td></tr>`;
                });
            }

        });
        html += "</tbody></table>";
        $("#dynamicContent").html(html);

    }).fail(function (xhr, status, exception) {
        console.log(xhr.status, xhr, status, exception);
    });

    event.preventDefault();
    // let html = "<table class='table table-bordered'>" +
    //     "<thead>" +
    //     "<tr><th scope='col'>#</th><th scope='col'>Item ID</th><th scope='col'>Item Name</th><th scope='col'>Description</th><th scope='col'>Category</th></tr>" +
    //     "</thead><tbody>";


    // let i = 0;
    // menuObj.data.breakfast.forEach(function (obj) {
    //     html += `<tr><th scope='row'>${++i}</th><td>${obj.itemId}</td><td>${obj.itemName}</td><td>${obj.description}</td><td>${obj.category}</td></tr>`;
    // });
    // html += "</tbody></table>";
    // $("#dynamicContent").html(html);
    // event.preventDefault();
});

$("#menuLunch").click(function (event) {
    $.ajax({
        "url": "http://localhost:8083/pick_n_go_app_war_exploded/menus",
        "type": "GET"
    }).done(function (menuObj) {
        let html = "<table class='table table-bordered'>" +
            "<thead>" +
            "<tr><th scope='col'>#</th><th scope='col'>Item ID</th><th scope='col'>Item Name</th><th scope='col'>Description</th></tr>" +
            "</thead><tbody>";


        let i = 0;

        menuObj.data.forEach(function (obj) {
            if (obj.category === "lunch") {
                obj.items.forEach(function (item) {
                    html += `<tr><th scope='row'>${++i}</th><td>${item.itemId}</td><td>${item.itemName}</td><td>${item.description}</td></tr>`;
                });
            }

        });
        html += "</tbody></table>";
        $("#dynamicContent").html(html);

    }).fail(function (xhr, status, exception) {
        console.log(xhr.status, xhr, status, exception);
    });

    event.preventDefault();
});

$("#menuDinner").click(function (event) {
    $.ajax({
        "url": "http://localhost:8083/pick_n_go_app_war_exploded/menus",
        "type": "GET"
    }).done(function (menuObj) {
        let html = "<table class='table table-bordered'>" +
            "<thead>" +
            "<tr><th scope='col'>#</th><th scope='col'>Item ID</th><th scope='col'>Item Name</th><th scope='col'>Description</th></tr>" +
            "</thead><tbody>";


        let i = 0;

        menuObj.data.forEach(function (obj) {
            if (obj.category === "dinner") {
                obj.items.forEach(function (item) {
                    html += `<tr><th scope='row'>${++i}</th><td>${item.itemId}</td><td>${item.itemName}</td><td>${item.description}</td></tr>`;
                });
            }

        });
        html += "</tbody></table>";
        $("#dynamicContent").html(html);

    }).fail(function (xhr, status, exception) {
        console.log(xhr.status, xhr, status, exception);
    });

    event.preventDefault();
});

$("#top5Items").click(function (event) {
    //make ajax call to top 5 items end point towards server-side {}
    // $.ajax({
    //     "url": "http://localhost:8080/web_war_exploded/Servlet",
    //     "type": "GET"
    // }).done(function (data) {
    //     console.log(JSON.parse('{ "name":"John", "age":30, "city":"New York"}'));
    // }).fail(function (xhr, status, exception) {
    //     console.log(xhr.status, xhr, status,exception);
    // });
    let jsonobj = JSON.parse('{ "name":"John", "age":30, "city":"New York"}');

    let jsonObject = {
        "statusCode": 200,
        "message": "Success",
        "data": [
            {
                "itemId": "RB3JT4",
                "itemName": "Banana juice",
                "description": "This is banana juice",
                "category": "lunch"
            },
            {
                "itemId": "EBiZBD",
                "itemName": "Orange juice",
                "description": "This is juice",
                "category": "breakfast"
            },
            {
                "itemId": "5uDnBM",
                "itemName": "Pizza",
                "description": "This is nice pizza",
                "category": "dinner"
            },
            {
                "itemId": "ET43678",
                "itemName": "Spaghetti",
                "description": "This is french Spaghetti",
                "category": "breakfast"
            },
            {
                "itemId": "B0091T5",
                "itemName": "Biriyanni",
                "description": "This is Biriyanni",
                "category": "Dinner"
            }
        ]
    }

    let html = "<table class='table table-bordered'>" +
        "<thead>" +
        "<tr><th scope='col'>#</th><th scope='col'>Item ID</th><th scope='col'>Item Name</th><th scope='col'>Description</th><th scope='col'>Category</th></tr>" +
        "</thead><tbody>";

    //for (var i = 1; i < 6; i++) {
    let i = 0;
    jsonObject.data.forEach(function (obj) {
        html += `<tr><th scope='row'>${++i}</th><td>${obj.itemId}</td><td>${obj.itemName}</td><td>${obj.description}</td><td>${obj.category}</td></tr>`;
    });

    //}


    html += "</tbody></table>";
    $("#dynamicContent").html(html);
    event.preventDefault();
});

$("#nutritionalFacts").click(function () {
    let html = "<div class='card border-info mb-3' style='max-width: 100%;'>" +
        "<div class='card-header'>About Calories</div><div class='card-body text-info'>" +
        "<h5 class='card-title'>Calories in food</h5><p class='card-text'>The number of calories in a food is a" +
        "measurement of the amount of energy stored in that food. Your body uses calories from food for walking," +
        "thinking, breathing, and other important functions. The average person needs to eat about 2,000 " +
        "calories every day to maintain their weight. However, a persons specific daily calorie intake can vary" +
        "depending on their age, gender, and physical activity level. Men generally need more calories than women, " +
        "and people who exercise need more calories than people who dont.</p></div></div>";
    $("#dynamicContent").html(html);
});

$("#addItemForm").submit(function (event) {
    //console.log("here");
    let itemName = $("#itemName").val();
    let description = $("#description").val();
    let category = $("#category").val();
    let submitFormErrors = $("#submitFormErrors");

    var newItemData = {
        "itemName": itemName,
        "description": description,
        "category": category
    };

    $.ajax({
        "url": "http://localhost:8083/pick_n_go_app_war_exploded/items",
        "type": "POST",
        "dataType": "json",
        "contentType": "application/json",
        "data": JSON.stringify(newItemData)
    }).done(function (data) {
        console.log(data);
        submitFormErrors.html("New Menu Item created successfully");
        submitFormErrors.css("display", "block");
        submitFormErrors.addClass("alert-success");
    }).fail(function (xhr, status, exception) {
        console.log(xhr.status, xhr, status, exception);
        submitFormErrors.html("Error: There was an error creating the Item");
        submitFormErrors.css("display", "block");
        submitFormErrors.addClass("alert-danger");
    });

    event.preventDefault();
});

//newMenuForm
$("#newMenuForm").submit(function (event) {
    let formData = $("#newMenuForm").serialize();
    console.log(formData);
    let category = $("#categoryMenu").val();
    let formDataArr = formData.split('=');
    formDataArr = formDataArr.filter(function (elem) {
        if (!elem.includes("&")) {
            return elem;
        }
    });

    let finalArr = [];
    formDataArr.forEach(function (elem) {
        let val = { "itemId": elem };
        finalArr.push(val);
    });
    console.log(finalArr);
    let newMenuErrors = $("#menuFormErrors");

    var newMenuData = {
        "category": category,
        "items": finalArr
    };

    $.ajax({
        "url": "http://localhost:8083/pick_n_go_app_war_exploded/menus",
        "type": "POST",
        "dataType": "json",
        "contentType": "application/json",
        "data": JSON.stringify(newMenuData)
    }).done(function (data) {
        newMenuErrors.html(`The Menu for ${category} has been created successfully`);
        newMenuErrors.css("display", "block");
        newMenuErrors.addClass("alert-success");
    }).fail(function (xhr, status, exception) {
        console.log(xhr.status, xhr, status, exception);
        newMenuErrors.html(`Error: There was an error creating your ${category} menu`);
        newMenuErrors.css("display", "block");
        newMenuErrors.addClass("alert-danger");
    });

    event.preventDefault();
});

$("#placeOrderForm").submit(function (event) {
    let params = (new URL(document.location)).searchParams;
    let name = params.get("name");
    let formData = $("#placeOrderForm").serialize();
    let formDataArr = formData.split('=');
    formDataArr = formDataArr.filter(function (elem) {
        if (!elem.includes("&")) {
            return elem;
        }
    });

    let finalArr = [];
    formDataArr.forEach(function (elem) {
        let val = { "itemId": elem };
        finalArr.push(val);
    });
    //console.log(finalArr);
    let orderFormErrors = $("#orderFormErrors");

    var newOrderData = {
        "name": name,
        "items": finalArr
    };

    $.ajax({
        "url": "http://localhost:8083/pick_n_go_app_war_exploded/orders",
        "type": "POST",
        "dataType": "json",
        "contentType": "application/json",
        "data": JSON.stringify(newOrderData)
    }).done(function (data) {
        //console.log(data);
        orderFormErrors.html("Your order has been created successfully");
        orderFormErrors.css("display", "block");
        orderFormErrors.addClass("alert-success");
    }).fail(function (xhr, status, exception) {
        console.log(xhr.status, xhr, status, exception);
        submitFormErrors.html("Error: There was an error creating your order");
        submitFormErrors.css("display", "block");
        submitFormErrors.addClass("alert-danger");
    });

    event.preventDefault();
});
//});