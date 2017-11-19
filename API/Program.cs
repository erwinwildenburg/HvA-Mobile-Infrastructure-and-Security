using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.Logging;

namespace API
{
    public class Program
    {
        public static void Main(string[] args)
        {
            BuildWebHost(args).Run();
        }

        private static IWebHost BuildWebHost(string[] args) =>
            WebHost.CreateDefaultBuilder(args)
                .UseApplicationInsights()
                .UseStartup<Startup>()
                .ConfigureAppConfiguration((context, config) =>
                {
                    IHostingEnvironment env = context.HostingEnvironment;
                    config.AddJsonFile(Path.Combine(AppContext.BaseDirectory, "appsettings.json"), true, true);
                    config.AddJsonFile(Path.Combine(AppContext.BaseDirectory, $"appsettings.{env.EnvironmentName}.json"), true, true);
                    config.AddEnvironmentVariables();
                    config.AddEnvironmentVariables("APPSETTING_");
                })
                .UseDefaultServiceProvider(options => options.ValidateScopes = false)
                .Build();
    }
}
