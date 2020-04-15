using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using HarrisContactWeb.netcore.Data;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;

namespace HarrisContactWeb.netcore
{
    public class Program
    {
        public static void Main(string[] args)
        {
            var host = CreateHostBuilder(args).Build();// creates a host

            using (var scope = host.Services.CreateScope())//creates a scope
            {
                var services = scope.ServiceProvider;
                try
                {
                    var context = services.GetRequiredService<HarrisDbContext>();
                    DbInitializer.Initialize(context);// Database initialization
                }
                catch (Exception ex)//try/catch expression so that the application does not explde from being unable to connect with the db file
                {
                    var logger = services.GetRequiredService<ILogger<Program>>();
                    logger.LogError(ex, "An error has occured while create the database");
                }
            }


            host.Run();
        }

        public static IHostBuilder CreateHostBuilder(string[] args) =>
            Host.CreateDefaultBuilder(args)
                .ConfigureWebHostDefaults(webBuilder =>
                {
                    webBuilder.UseStartup<Startup>();
                });
    }
}
