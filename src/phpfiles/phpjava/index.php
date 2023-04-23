<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles.css">
    <title>Simple Database</title>
</head>
<body>

    <form autocomplete="off" action="login.php" method="post">
        <h2>Login</h2>
        <?php if(isset($_GET['error'])) { ?>
            <p class="error"><?php echo$_GET['error']; ?></p>
        <?php } ?>
        <label autocomplete="off">User Name</label>
        <input autocomplete="off" type="text" name="uname" placeholder="User Name">

        <label>Password</label>
        <input type="password" name="password" placeholder="Password">

        <button type="submit">Login</button>
    </form>

</body>
</html>